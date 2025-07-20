/// <reference types="leaflet" />

var popup = L.popup();

const onEachFeature = function (feature, layer) {
  const description = feature.properties?.description || "No description";
  layer.bindPopup(description);
};

// Create base map centered on Estonia
const map = L.map('map').setView([58.5953, 25.0136], 7); 

L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
  attribution: '&copy; OpenStreetMap contributors',
}).addTo(map);

// Function to fetch spots from the backend and add to map
async function fetchMushroomSpots() {
  console.log("Fetching mushroom spots...");
  try {
    const response = await fetch("http://localhost:8080/mushroomspots");
    console.log("Response status:", response.status);
    // console.log("Response headers:", [...response.headers.entries()]);

    if (!response.ok) {
      throw new Error(`Error fetching mushroom spots: ${response.status}`);
    }

    const mushroomSpots = await response.json();
    console.log("Mushroom spots fetched successfully:", mushroomSpots);

    // Layer to add GeoJSON objects to the map
   L.geoJSON(mushroomSpots, {
    onEachFeature: onEachFeature
   }).addTo(map);

  } catch (error) {
    console.error("Error fetching mushroom spots:", mushroomSpots);
  }
}

// Function to add a new spot when user clicks on map
function onMapClick(e) {
    map.closePopup();

    const lat = e.latlng.lat;
    const lng = e.latlng.lng;

    // Add HTML form popup
    const formHtml = `
    <form id="addSpotForm">
      <label>Description:<br>
      <input type="text" id="descriptionInput" class="description-input" required/>
      </label><br>
      <button type="submit" class="add-spot-button">Add mushroom spot</button>
    </form>
    `;

    // Show popup form on map
    popup
      .setLatLng(e.latlng)
      .setContent(formHtml)
      .openOn(map);

    // Wait for form submission
    setTimeout(() => {
      const form = document.getElementById("addSpotForm");
      if (form) {
        form.addEventListener("submit", async function (event) {
          event.preventDefault();

          const description = document.getElementById("descriptionInput").value;

          // Build DTO to match MushroomSpotCreatDto in the backend
          const newSpot = {
            description: description,
            latitude:lat,
            longitude: lng
          };

          try {
            const response = await fetch("http://localhost:8080/mushroomspots", {
              method: "POST",
              headers: {
                "Content-Type": "application/json"
              },
              body: JSON.stringify(newSpot)
            });
          
            if (!response.ok) {
              throw new Error(`Failed to add spot: ${response.status}`);
            }

            const createdSpot = await response.json();
            console.log("Spot added:", createdSpot);

            L.geoJSON(createdSpot, {
              onEachFeature: onEachFeature
            }).addTo(map);
            map.closePopup();

          } catch (error) {
            console.error("Error adding spot: ", error);
          }
        });
      }
    }, 0);
}

// Initialize when page loads
document.addEventListener("DOMContentLoaded", function() {
  fetchMushroomSpots();
  map.on('click', onMapClick);
});

