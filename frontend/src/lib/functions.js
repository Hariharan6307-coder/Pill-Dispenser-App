export async function getTimings() {
  try {
    const response = await fetch("http://localhost:4000/get-timings");

    if (!response.ok) {
      throw new Error("Network response was not ok " + response.statusText);
    }

    const data = await response.json();

    console.log("Fetched data:", data);

    return data;
  } catch (error) {
    console.error("Error fetching users:", error);
    return [];
  }
}

