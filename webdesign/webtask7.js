function validateEmail(email) {
    // Regular expression for validating email format
    let emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (emailRegex.test(email)) {
      alert("Email is valid!");
    } else {
      alert("Please enter a valid email address.");
    }
  }
  