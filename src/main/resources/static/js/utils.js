function openModal(modalEle){
    modalEle.style.display = 'flex';
    document.body.style.overflow = 'hidden';
}

function closeModal(modalEle){
    modalEle.style.display = 'none';
    document.body.style.overflow = 'auto';
}