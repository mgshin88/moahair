var modal = document.getElementById('signUpModal');

	var modal2 = document.getElementById('LogInModal');

	$("#logIn").click(function() {
		$("#LogInModal").fadeIn(500);
	});

	$("#signUp").click(function() {
		$("#signUpModal").fadeIn(500);
	});

	window.onclick = function(event) {
		if (event.target == modal) {
			modal.style.display = "none";
		} else if (event.target == modal2) {
			modal2.style.display = "none";
		}
	}