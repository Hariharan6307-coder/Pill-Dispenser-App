export async function getTimings() {
  try {
    const response = await fetch("http://localhost:4000/get-timings");

    if (!response.ok) {
      throw new Error("Network response was not ok " + response.statusText);
    }

    const data = await response.json();

    return data;
  } catch (error) {
    console.error("Error fetching users:", error);
    return [];
  }
}

// frontend.js
export async function updateReminderTime(columnName, timeString) {
  const response = await fetch(`http://localhost:4000/update-time/`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({ columnName, timeString })
  });

  const data = await response.json();
  console.log(data);
}

// Example usage:
updateReminderTime(1, "08:00:00"); // sets 8:00 AM

