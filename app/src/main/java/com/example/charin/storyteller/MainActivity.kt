package com.example.charin.storyteller


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent


class MainActivity : AppCompatActivity(),GenerateView {
    var presenter = GeneratorPresenter(this)
//    var fileList = ArrayList<String>();
    val generateFile = "GenreIndex.txt"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.setFile(generateFile)
        initComponents()
    }

    override fun readFile(file: String):ArrayList<String> {
        val fileList = ArrayList<String>();
        val inputStream = assets.open(file)
        inputStream.bufferedReader().useLines { lines -> lines.forEach { fileList.add(it)} }
        return fileList
    }

    private fun initComponents() {

        genreButton.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                genreButton2.isChecked = false
                genreButton3.isChecked = false
            }})

        genreButton2.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                genreButton.isChecked = false
                genreButton3.isChecked = false
            }})

        genreButton3.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                genreButton.isChecked = false
                genreButton2.isChecked = false
            }})

        button.setOnClickListener(object:View.OnClickListener{
            override fun onClick(p0: View?) {
                presenter.resultText.delete(0,presenter.resultText.length)
                if(editText.text.isEmpty()){
                    presenter.resultText.append("The fools")
                }
                presenter.resultText.append(editText.text.toString())
                presenter.createCharacter()

                if(genreButton.isChecked){
                    presenter.generateAction()
                    presenter.readFromRandom()
                }
                else if (genreButton2.isChecked){
                    presenter.generateRomance()
                    presenter.readFromRandom()
                }
                else if (genreButton3.isChecked){
                    presenter.generateComedy()
                    presenter.readFromRandom()
                } else{
                    presenter.resultText.delete(0,presenter.resultText.length)
                    presenter.resultText.append("Go back and select Genre plz")
                }

                println(presenter.getResult())
                var intent: Intent = Intent(this@MainActivity, ResultActivity_01::class.java)
                intent.putExtra("result",presenter.getResult().toString())
                startActivity(intent)

            }
        })

        button2.setOnClickListener(object:View.OnClickListener{
            override fun onClick(p0: View?) {
                genreButton.isChecked = false
                genreButton2.isChecked = false
                genreButton3.isChecked = false
                editText.setText("")

            }
        })


    }



}
