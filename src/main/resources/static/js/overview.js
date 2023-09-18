// DOM elements
const addBtn = document.getElementById(ADD_BTN_ID);
const modal = document.getElementById(MODAL_ID);

addBtn.addEventListener('click', e => {
    console.log('CLICKED.');
    modal.style.display = 'block';
})