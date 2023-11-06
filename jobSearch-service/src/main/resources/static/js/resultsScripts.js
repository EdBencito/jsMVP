const socket = new SockJS('/ws'); // Connect to the WebSocket endpoint
const stompClient = Stomp.over(socket);

stompClient.connect({}, (frame) => {
    stompClient.subscribe('/topic/jobSearchResults', (response) => {
        // Handle incoming messages
        const responseContent = JSON.parse(response.body);
        displayMessage(responseContent);
    });
});

function sendMessage() {
    const message = document.getElementById('message').value;
    const location = document.getElementById('location').value;
    const distanceSelect = document.getElementById('dropdown');
    const distance = distanceSelect.options[distanceSelect.selectedIndex].value;


    const data = {
        'message': message,
        'location': location,
        'distance': distance
    };
    console.log(message)
    console.log(location)
    console.log(distance)

    stompClient.send('/app/JobSearchViaKeywordsAndLocation', {}, JSON.stringify(data));
}

function displayMessage(message) {
    const resultsDiv = document.getElementById('jobListings');

    // Clear any previous results
    resultsDiv.innerHTML = '';

    // Check if the message is an array
    if (Array.isArray(message)) {
        // Iterate through the job listings and create HTML elements for each listing
        message.forEach((job) => {
            const jobDiv = document.createElement('div');
            jobDiv.classList.add('job-listing');

            jobDiv.innerHTML = `
                <h3>${job.jobTitle}</h3>
                <div class="date-employer-container">
                    <p><strong>Posted on:</strong> ${job.date}</p>
                    <p><strong>Company:</strong> ${job.employerName}</p>
                </div>
                <p><strong>Applicants:</strong> ${job.applications}</p>
                <div class="location-salary-container">
                    <p><strong>Location:</strong> ${job.locationName}</p>
                    <p><strong>Salary:</strong> ${job.currency} ${job.minimumSalary} - ${job.maximumSalary}</p>
                </div>
                <p><strong>Job Description:</strong> ${job.jobDescription}</p>
                <a href="${job.jobUrl}" target="_blank">Read More</a>
            `;

            jobDiv.style.fontFamily = "'Source Sans Pro', sans-serif";
            resultsDiv.appendChild(jobDiv);
        });
    } else {
        // Handle the case where the response is not an array of job listings
        resultsDiv.innerHTML = 'No job listings found.';
    }
}