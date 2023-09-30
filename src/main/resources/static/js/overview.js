// Clicking the Add button.
ADD_BTN.addEventListener('click', e => {
    MODAL.style.display = 'flex';
});

// Changing modal inputs based on type.
TYPE.addEventListener('change', e => {
    [DAYS, FROM, TO, DEADLINE, ESTIMATED].forEach(item => item.classList.toggle('hidden'));
});

// Closing the Modal with X.
CLOSE.addEventListener('click', e => {
    MODAL.style.display = 'none';
});

