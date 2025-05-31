
const inputBtn = document.getElementById('input-btn');
const deleteBtn = document.getElementById('delete-btn');
const tabBtn = document.getElementById('tab-btn');
const inputEl = document.getElementById('input-el');
const ulEl = document.getElementById('ul-el');
let myLeads = [];
renderLeads();


function saveLead(){
    let inputVal = inputEl.value.trim();
    if (inputVal !== ''){
        if (!inputVal.startsWith('http') && !inputVal.startsWith('https')){
            inputVal = 'https://' + inputVal;
        }

        myLeads.push(inputVal);
        inputEl.value = '';
        localStorage.setItem('myLeads', JSON.stringify(myLeads));
        //local storage can only store strings
    }
    renderLeads();
}


function deleteLead(){
    myLeads = [];
    localStorage.clear();
    renderLeads();
}


function deleteThatLead(index){
    myLeads.splice(index, 1);
    localStorage.setItem('myLeads', JSON.stringify(myLeads));
    renderLeads();
}


function saveTab(){
    chrome.tabs.query({active: true, currentWindow: true}, function(tabs){
        let currTabLink = tabs[0].url;
        if (!currTabLink.startsWith('http') && !currTabLink.startsWith('https')){
            currTabLink = 'https://' + currTabLink;
        }
        myLeads.push(currTabLink);
        localStorage.setItem('myLeads', JSON.stringify(myLeads));
        renderLeads();
    });
}


function renderLeads(){
    let listItems = "";
    myLeads = JSON.parse(localStorage.getItem('myLeads'));
    if (myLeads === null){
        myLeads = [];
    }

    for (let i = 0; i < myLeads.length; i++){
        //listItems += "<li>" + myLeads[i] + "</li>"   //or
        // const li = document.createElement("li")
        // li.textContent = myLeads[i];
        // ulEl.append(li);


        //or with link <a> tag
        //listItems += "<li><a target='_blank' href='" + myLeads[i] + "'>" + myLeads[i] + "</a></li>"
        //for eg    <li><a href=' https://www.google.com '>www.google.com   </a></li>

        //or ` ` allows to write in multiple lines and allows to use ${} to insert variables
        listItems += `
            <li>
                <a target='_blank' href='${myLeads[i]}'>
                    ${myLeads[i]}
                </a>
                <button class="mini-delete-btns" data-index="${i}">X</button>
            </li>
        `
    }
    ulEl.innerHTML = listItems;

    document.querySelectorAll('.mini-delete-btns')
        .forEach((button) => {
            button.addEventListener('click', () => deleteThatLead(button.dataset.index))
        })
}


inputBtn.addEventListener('click', saveLead)
deleteBtn.addEventListener('dblclick', deleteLead)
tabBtn.addEventListener('click', saveTab)

