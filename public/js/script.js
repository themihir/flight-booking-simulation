const hostName = "http://localhost:8080";

$(document).ready(function () {
    M.AutoInit();

    var elems = document.querySelectorAll('.datepicker');
    var instances = M.Datepicker.init(elems, {
        format: 'yyyy-mm-dd',
    });
});

function store(key, value) {
    localStorage.setItem(key, JSON.stringify(value));
}

function retrieve(key) {
    return JSON.parse(localStorage.getItem(key));
}

function remove(key) {
    return localStorage.removeItem(key);
}

function login() {

    axios({
        method: 'post',
        url: `${hostName}/user/login`,

        data: {
            email: $('#email').val(),
            password: $('#password').val()
        }
    })
        .then(function (response) {
            if (response.data.validCredentials) {
                store('user', response.data.user);
                window.location.href = `${hostName}/dashboard.html`;
            } else {
                window.location.href = `${hostName}/login.html`;
            }
        })
}

function signup() {

    axios({
        method: 'post',
        url: `${hostName}/user/signup`,

        data: {
            userType: $('#userType').val(),
            firstName: $('#fname').val(),
            lastName: $('#lname').val(),
            password: $('#password').val(),
            address: $('#address').val(),
            dob: $('#dob').val(),
            photoId: $('#photoid').val(),
            mobileNo: $('#mobile').val(),
            email: $('#email').val(),
            gender: $('#gender').val()
        }
    })
        .then(function (response) {
            if (response.data.success) {
                window.location.href = `${hostName}/login.html`;
            } else {
                window.location.href = `${hostName}/signup.html`;
            }
        })

}

function search() {

    store('search', {
        origin: $('#origin').val(),
        destination: $('#destination').val(),
        numberOfPassengers: $('#passengers').val(),
    });

    axios({
        method: 'post',
        url: `${hostName}/search/search`,

        data: {
            origin: $('#origin').val(),
            destination: $('#destination').val(),
            departureDate: $('#departure').val(),
            returnDate: $('#arrival').val(),
            numberOfPassengers: $('#passengers').val(),
            flightClass: $('#flightClass').val(),
            flightType: $('#flightType').val()
        },
        headers: {
            'Content-Type': 'application/json',
            'userid': retrieve('user').userId
        }
    })
        .then(function (response) {
            if (response.data.success) {

                let resultsHtml = '';
                response.data.results.forEach(el => {
                    const escapedObject = encodeURIComponent(JSON.stringify(el));
                    resultsHtml += `
                    <div class="collection-item">
                        Airlines: ${el.airlines}<br>
                        Airport: ${el.airport}<br>
                        Flight Number: ${el.flightNumber}<br>
                        Flight Duration: ${el.flightDuration} minutes<br>
                        Departure Time: ${el.departureTime}<br>
                        Arrival Time: ${el.arrivalTime}<br>
                        DepartureDate: ${el.departureDate}<br>
                        Arrival Date: ${el.arrivalDate}<br>
                        Price: ${el.price}<br>
                        <br>
                        <button class="btn waves-effect waves-light" type="submit" name="action" onclick="bookAction('${escapedObject}')">Checkout</button>
                        <br>
                    </div>
                    `
                });

                $('#SearchResults').html(resultsHtml);

            } else {

            }
        })
}

function bookAction(obj) {
    const object = JSON.parse(decodeURIComponent(obj));
    store('booking', object);
    window.location.href = `${hostName}/checkout.html`;
}

function book() {

    const booking = retrieve('booking');
    const search = retrieve('search');
    const user = retrieve('user');

    axios({
        method: 'post',
        url: `${hostName}/flightbook/booking`,

        data: {
            userId: user.userId,
            userType: user.userType,
            firstName: user.firstName,
            lastName: user.lastName,
            airlines: booking.airlines,
            airport: booking.airport,
            flightNumber: booking.flightNumber,
            price: booking.price,
            extraBaggage: parseInt($('#bags').val()),
            validProofID: $('#idproof').val(),
            seatInformation: parseInt($('#seat').val()),
            insurance: $('#insurance').val(),
            origin: search.origin,
            destination: search.destination,
            departureDate: booking.departureDate,
            returnDate: booking.arrivalDate,
            numberOfPassengers: search.numberOfPassengers,
            flightClass: booking.flightClass,
            flightType: booking.flightType
        },
        headers: {
            'Content-Type': 'application/json',
            'userid': retrieve('user').userId
        }
    })
        .then(function (response) {
            if (response.data.success) {
                $('#bookingForm').addClass('hide');
                $('#bookButton').addClass('hide');
                $('#almostDone').text('Your booking has been confirmed!');

                $('#pnr').text(response.data.result.pnr);
                $('#lastName').text(response.data.result.lastName);
                $('#airport').text(response.data.result.airport);
                $('#departureDate').text(response.data.result.departureDate);
                $('#flightClass').text(response.data.result.flightClass);
                $('#flightNumber').text(response.data.result.flightNumber);

                $('#results').removeClass('hide');
            }
        })
}

function bookings() {
    // window.location.href = '${hostName}/managebooking.html';
}

function deleteBooking() {
    axios({
        method: 'post',
        url: `${hostName}/manageBooking/booking/delete`,
        data: {
            pnr: $('#pnr').val()
        },
        headers: {
            'Content-Type': 'application/json',
            'userid': retrieve('user').userId
        }
    })
        .then(function (response) {
            if (response.data.success) {
                window.location.href = `${hostName}/managebooking.html`;
            }
        })
}

function editBookingDone() {
    const baggage = document.getElementById('baggage2').value;
    const insurance = document.getElementById('insurance2').value
    const flightclass = document.getElementById('flightclass2').value
    const passengers = document.getElementById('passengers2').value
    const seatinfo = document.getElementById('seatinfo2').value

    axios({
        method: 'post',
        url: `${hostName}/manageBooking/booking/edit`,
        data: {
            pnr: $('#pnr').val(),
            extraBaggage: baggage,
            insurance: insurance,
            flightClass: flightclass,
            numberOfPassengers: passengers,
            seatInformation: seatinfo
        }
    })
        .then(function (response) {
            if (response.data.success) {
                window.location.href = `${hostName}/managebooking.html`;
            }
        })
}

function editBooking() {
    axios({
        method: 'post',
        url: `${hostName}/manageBooking/bookings`,
        data: {
            pnr: $('#pnr').val(),
            lastName: $('#lastname').val(),
        },
        headers: {
            'Content-Type': 'application/json',
            'userid': retrieve('user').userId
        }
    })
        .then(function (response) {
            if (response.data.success) {
                let resultsHtml = '';
                response.data.results.forEach(el => {
                    resultsHtml += `
                        <div class="collection-item" id="editBookings">
                            Number of Bags<input type="number" id="baggage2", value=${el.extraBaggage}>
                            Insurance<input type="text" id="insurance2", value=${el.insurance}>
                            Flight Class<input type="text" id="flightclass2", value=${el.flightClass}>
                            Passengers<input type="text" id="passengers2", value=${el.numberOfPessengers}>
                            Seat Info<input type="number" id="seatinfo2", value=${el.seatInformation}>
                            <button class="btn waves-effect waves-light" onclick="editBookingDone()"> Update </button>
                        </div>
                        `
                });

                $('#SearchResults').html(resultsHtml);
            } else {
            }
        })
}

function rescheduleFlight(bookingId) {
    window.location.href = `${hostName}/reschedule.html?bookingId=` + bookingId;
}

function managebooking() {
    axios({
        method: 'post',
        url: `${hostName}/manageBooking/bookings`,

        data: {
            pnr: $('#pnr').val(),
            lastName: $('#lastname').val(),
        },
        headers: {
            'Content-Type': 'application/json',
            'userid': retrieve('user').userId
        }
    })
        .then(function (response) {
            if (response.data.success) {
                let resultsHtml = '';
                response.data.results.forEach(el => {
                    resultsHtml += `
                        <div class="collection-item">
                            First Name: ${el.firstName}<br>
                            Last Name: ${el.lastName}<br>
                            Origin: ${el.origin}<br>
                            Destination: ${el.destination}<br>
                            Departure Date: ${el.departureDate}<br>
                            Return Date: ${el.returnDate}<br>
                            Seat Info: ${el.seatInfo}<br>
                            Price: ${el.price}<br>
                        </div>
                        <div class="collection-item">
                            <button class="btn waves-effect waves-light" type="submit" onclick="editBooking()">Edit Booking</button>
                            <button class="btn waves-effect waves-light" type="submit" onclick="deleteBooking()">Delete Booking</button>
                            <button class="btn waves-effect waves-light" type="submit" onclick="rescheduleFlight(${el.bookingId})">Reschedule Flight</button>
                        </div>
                        `
                });

                $('#SearchResults').html(resultsHtml);
            }
        })
}

function checkinBags() {

    axios({
        method: 'post',
        url: `${hostName}/logistics/checkinBaggage`,
        data: {
            "counterId": parseInt($('#counter').val()),
            "type": $('#payloadType').val(),
            "pnr": $('#pnr').val(),
            "bags": [
                {
                    "weight": parseInt($('#weight').val()),
                    "type": $('#bagType').val()
                }
            ]
        },
        headers: {
            'Content-Type': 'application/json',
            'userid': retrieve('user').userId
        }
    })
        .then(function (response) {
            if (response.data.success) {

                let resultsHtml = `
                    Checkin Successful!<br>

                    <br><br>

                    <button class="btn waves-effect waves-light" onclick="findRouteRedirect(${response.data.baggageIds[0]})">Find Route</button>
                
                `;

                $('#result').html(resultsHtml);

            }
        })
}

function findRouteRedirect(baggageId) {
    window.location.href = `${hostName}/findRoute.html?baggageId=` + baggageId;
}

function getBaggage() {
    const baggageId = $('#baggageId').val();

    axios({
        method: 'post',
        url: `${hostName}/logistics/getBaggageState`,
        data: {
            "baggageId": baggageId
        },
        headers: {
            'Content-Type': 'application/json',
            'userid': retrieve('user').userId
        }
    })
        .then(function (response) {
            if (response.data.success) {
                let resultsHtml = '';

                resultsHtml += `
                        <div class="collection-item">
                            <b>Status</b>: ${response.data.baggageStatus}<br><br>
                            <button class="btn waves-effect waves-light" onclick="redirectTransit(${baggageId})">Transit Baggage</button>
                        </div>
                        `

                $('#results').html(resultsHtml);
            }
        })
}

function redirectTransit(id) {
    window.location.href = `${hostName}/baggageTransit.html?baggageId=` + id;
}

function updateBaggageState() {
    const urlParams = new URLSearchParams(window.location.search);
    let baggageId = urlParams.get('baggageId')

    axios({
        method: 'post',
        url: `${hostName}/logistics/registerTransition`,
        data: {
            "baggageId": baggageId,
            "transition": $('#bagType').val()
        },
        headers: {
            'Content-Type': 'application/json',
            'userid': retrieve('user').userId
        }
    })
        .then(function (response) {
            if (response.data.success) {
                let resultsHtml = '';

                resultsHtml += `
                        <div class="collection-item">
                            Baggage state updates successfully!<br>
                        </div>
                        `

                $('#results').html(resultsHtml);
            }
        })
}

function voucher() {
    axios({
        method: 'get',
        url: `${hostName}/helpAndSupport/voucher`,
        headers: {
            'Content-Type': 'application/json',
            'userid': retrieve('user').userId
        }
    })
        .then(function (response) {
            if (response.data.success) {
                let resultsHtml = '';
                    resultsHtml += `
                        <div class="center">
                            <H4 class="light">Hurray..! you got a free discount voucher</H4>
                        </div>
                        <div class="collection-item">
                            <b>Voucher:</b> ${response.data.results.voucher}<br>
                            <b>Voucher Discount:</b> ${response.data.results.discount}<br>
                        </div>
                        `
                $('#SearchResults').html(resultsHtml);
            }
        })
}

function payment() {
    axios({
        method: 'get',
        url: `${hostName}/helpAndSupport/payment`,
        headers: {
            'Content-Type': 'application/json',
            'userid': retrieve('user').userId
        }
    })
        .then(function (response) {
            if (response.data.success) {
                let resultsHtml = '';

                    resultsHtml += `
                        <div class="collection-item"><br>
                            <b>Payment:</b> ${response.data.results.Payment}<br>
                        </div>
                        `

                $('#SearchResults').html(resultsHtml);
            }
        })
}
function webcheckin(){
    axios({
        method: 'post',
        url: `${hostName}/webcheckin/webcheckin`,
        data: {
            pnr: $('#pnr').val(),
            lastName: $('#lastname').val(),
            dob: $('#dob').val()
        },
        headers: {
            'Content-Type': 'application/json',
            'userid': retrieve('user').userId
        }
    })
        .then(function (response) {


            if(response.data.success){

                let resultsHtml = '';

                resultsHtml += `
                    <div class="collection-item">
                       Status: ${response.data.result.activeStatus}<br>
                       Departure Date: ${response.data.result.departureDate}<br>
                       Destination: ${response.data.result.destination}<br>
                       Extra Baggage: ${response.data.result.extraBaggage}<br>
                       First Name: ${response.data.result.firstName}<br>
                       Flight Number: ${response.data.result.flightNumber}<br>
                       Insurance: ${response.data.result.insurance}<br>
                       Last Name: ${response.data.result.lastName}<br>
                       PNR: ${response.data.result.pnr}<br>
                       Seat Information: ${response.data.result.seatinformation}<br>                      
                    </div>
                       <br>
                       <br>
                       
                       <div class="row">
                       
                       <div class="col s3 input-field">
                            <input id="seatnumber" type="text" value="">
                            <label for="seatnumber">Seat Number</label>
                       </div>
                       
                       <div class="col s3 input-field">
                            <input id="extrabaggage" type="text" value="">
                            <label for="extrabaggage">Extra Baggage</label>
                       </div>               
</div>
<div class="row">
        <div class="col s12 center">
            <button class="btn waves-effect waves-light" type="submit" onclick="webcheckinCheckout()">Checkin</button>
        </div>
    </div>
    <div class="row"></div>
    <div class="row">
    <div class="col s12 center hide" id="successMessge">Your information is updated successfully!</div>
    <div>
    
    
                    `

                $('#results').html(resultsHtml);

            }

        })
}

function webcheckinCheckout(){
    axios({
        method: 'post',
        url: `${hostName}/webcheckin/checkout`,
        data: {
            seatNumber: $('#seatnumber').val(),
            extraBaggage: $('#extrabaggage').val()
        },
        headers: {
            'Content-Type': 'application/json',
            'userid': retrieve('user').userId
        }
    })
        .then(function (response) {
            $('#successMessge').removeClass('hide');
        })
}


function rescheduleCheckout(data){
    axios({
        method: 'post',
        url: `${hostName}/reschedule/confirm`,
        headers: {
            'Content-Type': 'application/json',
            'userid': retrieve('user').userId
        },
        data
    })
    .then(function (response) {
        console.log(response.data);
        $('#results').addClass('hide');
        $('#successMessage').removeClass('hide');
    })
}

function feedback(){

    let resultsHtml = '';

    resultsHtml += `
                        <div class="row">
                              <div class="row">
                                <div class="input-field col s12">
                                  <textarea id="feedbackArea" class="materialize-textarea"></textarea>
                                  <label for="feedbackArea">Please enter your feedback here</label>
                                </div>
                                <div class="s12 center">
                                <button class="btn waves-effect waves-light" onclick="submitFeedback()">Submit</button>
                                </div>
                              </div>
                          </div>
                        `

    $('#SearchResults').html(resultsHtml);

}

function submitFeedback(){
    axios({
        method: 'post',
        url: `${hostName}/helpAndSupport/feedback`,
        data: {
            feedback: $('#feedbackArea').val()
        },
        headers: {
            'Content-Type': 'application/json',
            'userid': retrieve('user').userId
        }
    })
        .then(function (response) {
            console.log(response.data);
            $('#SearchResults').addClass('hide');
            $('#successMessage').removeClass('hide');
        })
}