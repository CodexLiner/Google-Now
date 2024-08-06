package me.meenagopal24.googlenow

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.meenagopal24.googlenow.adapter.TextData
import me.meenagopal24.googlenow.adapter.TextListAdapter
import me.meenagopal24.googlenow.databinding.DemoBinding
import java.util.Locale

class MainActivity : ComponentActivity() {
    private lateinit var textToSpeech: TextToSpeech
    private lateinit var binding : DemoBinding
    private lateinit var adapter: TextListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = TextListAdapter { text, _ ->
            textToSpeech.setPitch(1.2f)
            textToSpeech.setSpeechRate(0.8f)

            if (textToSpeech.isSpeaking) textToSpeech.stop() else speak(text.text)
        }
        binding.dialogButton.setOnClickListener {
            if (binding.textInput.text.isNotEmpty()){
                speak(binding.textInput.text.toString())
            }
        }
        findViewById<RecyclerView>(R.id.text_rv).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
        val textDataList = mutableListOf(
            TextData(
                text = "नमस्ते, आप कैसे हैं??",
                heading = "Greeting"
            ),
            TextData(
                text = "Twinkle twinkle little star, how I wonder what you are",
                heading = "Greeting"
            ),
            TextData(
                text = "I just heard you found the one you've been looking, looking for\n" +
                        "I wish I would have known that wasn't me\n" +
                        "'Cause even after all the time I still wonder\n" +
                        "Why I can't move on\n" +
                        "Just the way you did so easily",
                heading = "Greeting"
            ),
            TextData(
                text = "संसार के समग्र विकास के लिए नवाचार अत्यंत आवश्यक है।?",
                heading = "Greeting"
            ),
            TextData(
                text = "कर्मण्येवाधिकारस्ते मा फलेषु कदाचन। मा कर्मफलहेतुर्भूर्मा ते सङ्गोऽस्त्वकर्मणि।।",
                heading = "Greeting"
            ),
            TextData(
                text = "Mujhe Bharatiya khana bahut pasand hai.",
                heading = "Food Preference"
            ),
            TextData(
                text = "Aapka din shubh ho!",
                heading = "Well Wishes"
            ),
            TextData(
                text = "Main Hindi seekh raha hoon.",
                heading = "Learning Hindi"
            ),
            TextData(
                text = "Aaj mausam bahut accha hai.",
                heading = "Weather"
            ),
            TextData(
                text = "Yeh vishwavidyalaya rashtriya maamle mein ek pramukh kendra hai.",
                heading = "University"
            ),
            TextData(
                text = "Mujhe yeh samajh aata hai ki aapka drishtikon kuchh alag hai.",
                heading = "Perspective"
            ),
            TextData(
                text = "Yeh ek mahatvapurn vishay hai jiska samadhan avashyak hai.",
                heading = "Important Issue"
            ),
            TextData(
                text = "Samajik aur arthik vikas ke liye sushasan atyant avashyak hai.",
                heading = "Governance"
            ),
            TextData(
                text = "Prakritik sansadhan ka satik upayog samajik uthan ke liye zaroori hai.",
                heading = "Natural Resources"
            ),
            TextData(
                text = "Sahasik aur samarth leadership naye vikas ki disha tay karti hai.",
                heading = "Leadership"
            )
        )
        textToSpeech = TextToSpeech(this) {
            val langResult = textToSpeech.setLanguage(Locale("hi", "IN"))
            var x = 0
            textToSpeech.voices?.let { voices ->
                voices.toList().let {
                    while (x < voices.size && x < textDataList.size) {
                        textDataList[x].voice = it[x]
                        x++
                    }
                }
                adapter.setData(textDataList)
            }
            if (langResult == TextToSpeech.LANG_MISSING_DATA || langResult == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(this, "Error Something went wrong", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun speak(text: String) {
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }
}