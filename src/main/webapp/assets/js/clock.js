"use strict";

const clock = document.getElementById("time");

function updateClock() {
    const now = new Date();
    clock.textContent = now.toLocaleTimeString();
    setTimeout(updateClock, 1000);
}

updateClock();
