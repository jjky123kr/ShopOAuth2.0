document.addEventListener('DOMContentLoaded', function() {
  var form = document.getElementById('signup-form');

  form.addEventListener('submit', function(event) {
    event.preventDefault();

    resetErrors();

    var data = {
      username: getValueById('username'),
      password: getValueById('password'),
      name: getValueById('name'),
      email: getValueById('email')
    };

    axios.post('/auth/joinProc', data, {
      headers: {
        'Content-Type': 'application/json'
      }
    })
    .then(function(response) {
      alert('Registration completed successfully.');
      location.href = '/login';
      console.log(response.data);
    })
    .catch(function(error) {
      if (error.response && error.response.data) {
        var errors = error.response.data;

        for (var field in errors) {
          if (errors.hasOwnProperty(field)) {
            var fieldElement = document.getElementById(field);
            var errorElement = document.getElementById(field + '-error');

            if (fieldElement && errorElement) {
              fieldElement.classList.add('error-field');
              errorElement.innerText = errors[field];
            }
          }
        }
      } else {
        console.error(error);
      }
    });
  });

  function getValueById(id) {
    return document.getElementById(id).value;
  }

  function resetErrors() {
    var fields = document.querySelectorAll('.error-field');
    var errorMessages = document.querySelectorAll('.error-message');

    fields.forEach(function(field) {
      field.classList.remove('error-field');
    });

    errorMessages.forEach(function(errorMessage) {
      errorMessage.innerText = '';
    });
  }
});
