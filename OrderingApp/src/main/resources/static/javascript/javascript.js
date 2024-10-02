let imageFile;

document.getElementById('image').addEventListener('change', function(event) {
	const file = event.target.files[0]; // Get the selected file
	if (file) {
		imageFile = file; // Store the file for later use
		const reader = new FileReader();
		reader.onload = function(e) {
			document.getElementById('imagePreview').src = e.target.result; // Preview the image
			document.getElementById('imagePreview').style.display = 'block'; // Show preview
		};
		reader.readAsDataURL(file); // Read the file as a data URL
	}
});

document.addEventListener("DOMContentLoaded", function() {
	const dropArea = document.querySelector(".file-drop-area");
	const fileInput = document.getElementById("image");
	const previewImage = document.getElementById("imagePreview");
	let selectedFile; // Variable to store the selected file

	// Open file dialog when clicking on the drop area
	dropArea.addEventListener("click", () => {
		fileInput.click();
	});

	// Handle drag over event
	dropArea.addEventListener("dragover", (event) => {
		event.preventDefault();
		dropArea.classList.add("drag-over");
	});

	// Handle drag leave event
	dropArea.addEventListener("dragleave", () => {
		dropArea.classList.remove("drag-over");
	});

	// Handle file drop event
	dropArea.addEventListener("drop", (event) => {
		event.preventDefault();
		const files = event.dataTransfer.files;
		if (files.length > 0) {
			selectedFile = files[0]; // Store the selected file
			handleFiles([selectedFile]); // Process the file for preview
		}
		dropArea.classList.remove("drag-over");
	});

	// Handle file input change event
	fileInput.addEventListener("change", () => {
		selectedFile = fileInput.files[0]; // Store the selected file
		handleFiles([selectedFile]); // Process the file for preview
	});

	// Function to handle file preview
	function handleFiles(files) {
		const file = files[0];
		if (file) {
			const reader = new FileReader();
			reader.onload = function(e) {
				previewImage.src = e.target.result; // Show image preview
				previewImage.style.display = "block"; // Make the preview visible
			};
			reader.readAsDataURL(file); // Read the file as a data URL
		}
	}

	// Optional: Handle form submission to include the selected file
	const form = document.querySelector("form"); // Get the form element
	form.addEventListener("submit", function() {
		if (selectedFile) {
			fileInput.files = [selectedFile]; // Assign the selected file to the input for upload
		}
	});
});

function confirmDelete(button) {
	const dishesSize = button.getAttribute('data-dishes-size');
	const categoryName = button.getAttribute('data-category-name');
	const deleteUrl = button.getAttribute('data-delete-url');

	const message = dishesSize > 0
		? "Category " + categoryName + " has " +  dishesSize +" dish(es). Are you sure you want to delete it?"
		: "Are you sure you want to delete category " + categoryName + "?";

	if (confirm(message)) {
		window.location.href = deleteUrl;
	}
}