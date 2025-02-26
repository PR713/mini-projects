
const inputBtn = document.getElementById('input-btn');
const inputEl = document.getElementById('input-el');
const ulEl = document.getElementById('ul-el');
let myLeads = [];

function saveLead(){
    if (inputEl.value !== ''){
        myLeads.push(inputEl.value);
        inputEl.value = '';
    }
    ulEl.innerHTML = '';
    for (let i = 0; i < myLeads.length; i++){
        ulEl.innerHTML += "<li>" + myLeads[i] + "</li>"
    }
}

inputBtn.addEventListener('click', saveLead);

