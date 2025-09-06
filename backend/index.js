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

const PORT = process.env.PORT || 4000;
app.listen(PORT, () => console.log(`✅ Backend running on http://localhost:${PORT}`));
