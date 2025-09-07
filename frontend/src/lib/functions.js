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

export async function updatePillData(pillData) {
  const response = await fetch(`http://localhost:4000/update-weekly-data`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({ pillData })
  });

  const data = await response.json();
  console.log(data);
}

// Reads the weekly_pill_data column for a given pill_box_id from Supabase
export async function fetchWeeklyPillData() {
  const response = await fetch("http://localhost:4000/get-weekly-pill-data", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
  });
  const result = await response.json();
  // If the column contains JSON as text, parse it before returning
  if (result.weekly_pill_data) {
    result.weekly_pill_data = JSON.parse(result.weekly_pill_data);
  }
  return result;
}

export async function flipTheFlap() {
  const res = await fetch("http://localhost:4000/flip-flap");
  const data = await res.json();
  const text = data.message; // Show LED status ("LED is on/off")
}

export async function rotateTheBox() {
  const res = await fetch("http://localhost:4000/rotate");
  const data = await res.json();
  const text = data.message; // Show LED status ("LED is on/off")
}

// Example usage:
updateReminderTime(1, "08:00:00"); // sets 8:00 AM

