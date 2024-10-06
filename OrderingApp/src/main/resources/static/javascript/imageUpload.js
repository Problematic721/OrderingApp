document.addEventListener("DOMContentLoaded", function() {
	const fileInput = document.getElementById("image");
	const previewImage = document.getElementById("imagePreview");
	const deleteImageCheckbox = document.getElementById("deleteImage");

	// Handle the file input change to show the image preview
	if (fileInput) {
		fileInput.addEventListener("change", function(event) {
			const file = event.target.files[0];
			if (file) {
				const reader = new FileReader();
				reader.onload = function(e) {
					previewImage.src = e.target.result;
					previewImage.style.display = "block";
				};
				reader.readAsDataURL(file);
			} else {
				previewImage.src = "";
				previewImage.style.display = "none";
			}
		});
	}

	// Handle removal of the image by hiding the preview and resetting the file input
	if (deleteImageCheckbox) {
		deleteImageCheckbox.addEventListener("change", function() {
			if (this.checked) {
				previewImage.src = "";
				previewImage.style.display = "none";
				fileInput.value = ""; // Reset the file input
			}
		});
	}
});

document.addEventListener("DOMContentLoaded", function() {
	const fileInput = document.getElementById("image");
	const previewImage = document.getElementById("imagePreview");
	const removeImageButton = document.getElementById("removeImageBtn");
	const deleteImageInput = document.getElementById("deleteImageInput");

	// Handle the file input change to show the image preview
	if (fileInput) {
		fileInput.addEventListener("change", function(event) {
			const file = event.target.files[0];
			if (file) {
				const reader = new FileReader();
				reader.onload = function(e) {
					previewImage.src = e.target.result;
					previewImage.style.display = "block";
				};
				reader.readAsDataURL(file);
				// Ensure that the delete flag is reset if a new image is uploaded
				deleteImageInput.value = "false";
				removeImageButton.style.display = "block";
			} else {
				previewImage.src = "";
				previewImage.style.display = "none";
				removeImageButton.style.display = "none";
			}
		});
	}

	// Handle image removal (both frontend preview and setting delete flag)
	if (removeImageButton) {
		removeImageButton.addEventListener("click", function(event) {
			event.preventDefault();
			previewImage.src = "";
			previewImage.style.display = "none";
			fileInput.value = ""; // Reset file input
			deleteImageInput.value = "true"; // Mark the image for deletion
			removeImageButton.style.display = "none"; // Hide the button after image removal
		});
	}
});