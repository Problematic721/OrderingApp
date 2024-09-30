const fileInput = document.getElementById('image');
const fileDropArea = document.querySelector('.file-drop-area');

fileDropArea.addEventListener('dragover', (e) => {
	e.preventDefault();
	fileDropArea.classList.add('active');
});

fileDropArea.addEventListener('dragleave', () => {
	fileDropArea.classList.remove('active');
});

fileDropArea.addEventListener('drop', (e) => {
	e.preventDefault();
	fileDropArea.classList.remove('active');
	fileInput.files = e.dataTransfer.files;
});