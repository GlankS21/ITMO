function loadMap() {
  const map = document.getElementById("map").contentDocument.querySelector("svg");
  const mapDesc = document.getElementById("map-desc");
  const title = document.getElementById("title");
  let previousTarget = null;
  let zoomedElement = null;
  let isZoomed = false;
  map.addEventListener("mousemove", (e) => {
    const target = e.target.closest("g");
    if (target) {
      target.style.opacity = 0.6;
      mapDesc.style.transform = `translate(${e.offsetX}px, ${e.offsetY}px)`;
      mapDesc.textContent = target.id;
      mapDesc.style.display = "block";
    }
  }, false);
  map.addEventListener("mouseout", (e) => {
    const target = e.target.closest("g");
    if (target) {
      target.style.opacity = 1; 
      mapDesc.style.display = "none";
    }
  }, false);
  map.addEventListener("click", (e) => {
    const target = e.target.closest("g");
    if (target) {
      if (previousTarget && previousTarget !== target)  previousTarget.style.fill = "";
      target.style.fill = "#E68A2E  ";
      previousTarget = target;
      const { x, y, width, height } = target.getBBox();
      const centerX = x + width / 2;
      const centerY = y + height / 2;
      const transformOriginX = (centerX / map.viewBox.baseVal.width) * 100;
      const transformOriginY = (centerY / map.viewBox.baseVal.height) * 100;
      if (isZoomed && zoomedElement === target) {
        map.style.transform = "scale(1)";
        map.style.transformOrigin = "center center";
        isZoomed = false;
        zoomedElement = null;
      } else {
        map.style.transform = "scale(2)";
        map.style.transformOrigin = `${transformOriginX}% ${transformOriginY}%`;
        isZoomed = true;
        zoomedElement = target;
      }
      title.innerHTML = `${target.id}`;
    } else {
      if (isZoomed) {
        map.style.transform = "scale(1)";
        map.style.transformOrigin = "center center";
        isZoomed = false;
        zoomedElement = null;
      }
      title.innerHTML = `Карта <br class="title-space"> Санкт-Петербуга`;
    }
  }, false);
}
window.onload = function () {
  loadMap();
};
