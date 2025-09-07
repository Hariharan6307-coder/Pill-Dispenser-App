import express from "express";
import cors from "cors";
import dotenv from "dotenv";
import { createClient } from "@supabase/supabase-js";

dotenv.config();
const app = express();
app.use(cors());
app.use(express.json());

// Connect to Supabase
const supabase = createClient(process.env.SUPABASE_URL, process.env.SUPABASE_KEY);
const ESP32_IP = "http://10.55.139.208"; 

app.get("/flip-flap", async (req, res) => {
  try {
    const response = await fetch(`${ESP32_IP}/flap/open`);
    const text = await response.text();
    res.json({ message: text });
  } catch (err) {
    res.status(500).json({ error: "Failed to toggle ESP32", details: err.message });
  }
});

app.get("/rotate", async (req, res) => {
  try {
    const response = await fetch(`${ESP32_IP}/rotate`);
    const text = await response.text();
    res.json({ message: text });
  } catch (err) {
    res.status(500).json({ error: "Failed to toggle ESP32", details: err.message });
  }
});

app.get("/get-timings", async (req, res) => {
  const { data, error } = await supabase
    .from("users")
    .select("morning_time, evening_time, night_time")
    .single(); 

  if (error) {
    return res.status(500).json({ error: error.message });
  }

  res.json(data);
});

app.put("/update-time", async (req, res) => {
  const { columnName, timeString } = req.body; // expected format "HH:MM:SS"

  const { data, error } = await supabase
    .from("users")                 // replace with your table name
    .update({ [columnName]: timeString })         // directly assign time string
    .eq("pill_box_id", 69420);

  if (error) {
    return res.status(500).json({ error: error.message });
  }

  res.json({ message: "Time updated successfully", data });
});

app.post("/get-weekly-pill-data", async (req, res) => {
  const { data, error } = await supabase
    .from("users")
    .select("weekly_pill_data")
    .eq("pill_box_id", 69420)
    .single();

  if (error) {
    return res.status(500).json({ error: error.message });
  }
  res.json(data);
});

app.put("/update-weekly-data", async (req, res) => {
  const { pillData } = req.body; // expected format "HH:MM:SS"

  const { data, error } = await supabase
    .from("users")                 // replace with your table name
    .update({ weekly_pill_data: pillData }) // directly assign time string
    .eq("pill_box_id", 69420);

  if (error) {
    return res.status(500).json({ error: error.message });
  }

  res.json({ message: "Time updated successfully", data });
});

const PORT = process.env.PORT || 4000;
app.listen(PORT, () => console.log(`✅ Backend running on http://localhost:${PORT}`));
