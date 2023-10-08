function openModal(modalEle){
    modalEle.style.display = 'flex';
    document.body.style.overflow = 'hidden';
}

function closeModal(modalEle){
    modalEle.style.display = 'none';
    document.body.style.overflow = 'auto';
}

// Gets value of input, select and textarea tags inside the {parent}.
function getValue(parent){
    let userInput;
    if((userInput = parent.querySelector('input')) !== null) return userInput.value;
    else if((userInput = parent.querySelector('select')) !== null) return userInput.value;
    else if((userInput = parent.querySelector('textarea')) !== null) return userInput.value;
    else return null;
}