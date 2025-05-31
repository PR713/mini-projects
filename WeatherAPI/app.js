// 154ce9c4290971119d1e19bac1a4e4ad


const date = document.getElementById("date");
const city = document.getElementById("city");
const temp = document.getElementById("temp");
const tempImg = document.getElementById("tempImg");
const description = document.getElementById("description");
const tempMax = document.getElementById("tempMax");
const tempMin = document.getElementById("tempMin");
const months = ["January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December"];
const searchInput = document.getElementById("searchInput")

let dateObj = new Date();
let month = months[dateObj.getUTCMonth()];
let day = dateObj.getUTCDay() - 1;
let year = dateObj.getUTCFullYear();

date.innerHTML = `${month} ${day} ${year}`;

const app = document.getElementById("app");

const getWeather = async () => {
    try {
        const cityName = searchInput.value;
        const weatherData = await fetch(
            `https://api.openweathermap.org/data/2.5/weather?q=${cityName}&appid=154ce9c4290971` +
            '119d1e19bac1a4e4ad&units=metric', {
                headers: {
                    Accept: 'application/json',
                }
            });

        if (!weatherData.ok){
            alert('City not found');
            return;
        }

        const weatherDataConvertToJsObjs = await weatherData.json();
        console.log(weatherDataConvertToJsObjs);
        city.innerHTML = `${weatherDataConvertToJsObjs.name}`;
        description.innerHTML = `${weatherDataConvertToJsObjs.weather[0].main}`;
        tempImg.innerHTML = `<img src="http://openweathermap.org/img/wn/${weatherDataConvertToJsObjs.weather[0].icon}.png" />`;
        temp.innerHTML = `<h2>${Math.round(weatherDataConvertToJsObjs.main.temp)}°C</h2>`;
        tempMax.innerHTML = `${weatherDataConvertToJsObjs.main.temp_max}°C`;
        tempMin.innerHTML = `${weatherDataConvertToJsObjs.main.temp_min}°C`;

    } catch (e) {
        console.error(e);
    }
}

const searchIcon = document.getElementById("searchIcon");
searchIcon.addEventListener("click", getWeather);
searchInput.addEventListener("keypress", (event) => {
    if (event.key === "Enter") {
        getWeather();
    }
});