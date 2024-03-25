document.getElementById('deliveryForm').addEventListener('submit', function(event) {
    event.preventDefault();

    // Get form data
    var formData = new FormData(this);

    // Build URL with query parameters
    var url = 'http://localhost:8080/delivery/fee?city=' + encodeURIComponent(formData.get('city')) + '&vehicleType=' + encodeURIComponent(formData.get('vehicleType'));

    // Send GET request to API endpoint
    fetch(url)
        .then(response => response.text())
        .then(data => {
            // Display response
            document.getElementById('result').innerText = data;
        })
        .catch(error => {
            console.error('Error:', error);
        });
});

function selectCity(city) {
    document.getElementById('selectedCity').value = city;
    // Remove active class from all buttons
    document.querySelectorAll('#cityButtons button').forEach(btn => btn.classList.remove('active'));
    // Add active class to the selected button
    document.querySelectorAll('#cityButtons button').forEach(btn => {
        if (btn.textContent.trim() === city) {
            btn.classList.add('active');
        }
    });
}

function selectVehicle(vehicle) {
    document.getElementById('selectedVehicle').value = vehicle;
    // Remove active class from all buttons
    document.querySelectorAll('#vehicleButtons button').forEach(btn => btn.classList.remove('active'));
    // Add active class to the selected button
    document.getElementById(vehicle.toLowerCase() + 'Btn').classList.add('active');
}