let canvases = {baseImage:'',modification:'',finished:''};
let ctxs = {baseImage:'',modification:'',finished:''};
let filenames = {baseImage:'',modification:''};
window.addEventListener('load',()=>{
  canvases.baseImage = document.getElementById('baseImage');
  canvases.modification = document.getElementById('modification');
  canvases.finished = document.getElementById('finished');

  document.getElementById('download').addEventListener('click',()=>{
    let url = canvases.finished.toDataURL();
    let a = document.createElement('a');
    a.href=url;
    a.target="_blank";
    a.download = `suspicious_${filenames.baseImage}_${filenames.modification.split("_")[filenames.modification.split("_").length-1]}.png`;
    document.body.appendChild(a);
    a.click();
    document.body.removeChild(a);
  })

  for (let key of Object.keys(canvases)) {
    if (!canvases.hasOwnProperty(key)) continue;
    canvases[key].height = canvases[key].width = 16;
    canvases[key].style.height = canvases[key].style.width = 64;
    ctxs[key] = canvases[key].getContext('2d');
    canvases[key].addEventListener('dragover',(ev)=>{
      ev.preventDefault();
    });
    if (key != "finished") {
      canvases[key].addEventListener('drop',(ev)=>{
        ev.preventDefault();
        if (ev.dataTransfer.items) {
          [...ev.dataTransfer.items].forEach((item,i)=>{
            if (item.kind === "file") {
              const file = item.getAsFile();
              if (!file.name.endsWith('.png')) return;
              filenames[key] = file.name.replace(".png","");
              createImageBitmap(file,0,0,16,16).then(bitmap=>{
                ctxs[key].clearRect(0,0,16,16);
                ctxs[key].drawImage(bitmap,0,0);
                updateFinished();
              })
            }
          })
        }
      })
    } else {
      canvases[key].addEventListener('drop',(ev)=>{
        ev.preventDefault();
      });
    }
  }
});

function updateFinished() {
  ctxs.finished.clearRect(0,0,16,16);
  ctxs.finished.drawImage(canvases.baseImage,0,0);
  ctxs.finished.drawImage(canvases.modification,0,0);
}