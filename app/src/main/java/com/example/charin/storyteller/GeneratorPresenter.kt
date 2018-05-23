package com.example.charin.storyteller

import java.io.InputStream
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by kaizofaria on 21/5/2018 AD.
 */
class GeneratorPresenter(val view: GenerateView) {
    var headNum = 0
    var middleNum = 0
    var endNum = 0
    val generator = Random()
    var charList = ArrayList<String>()
    var fileListPresenter = ArrayList<String>()
    var resultList = ArrayList<String>()
    var resultText = StringBuilder()

    fun setFile(filename: String) {
        this.fileListPresenter = view.readFile(filename)
    }

    fun createCharacter() {
        var char = generator.nextInt(3 - 0) + 0
        this.charList = view.readFile("CharacterType.txt")
        readFile(charList.get(char))
        appendString(resultList)
    }

    fun generateRomance() {
        this.headNum = generator.nextInt(2 - 0) + 0
        this.middleNum = generator.nextInt(4 - 2) + 2
        this.endNum = generator.nextInt(6 - 4) + 4
    }

    fun generateAction() {
        this.headNum = generator.nextInt(8 - 6) + 6
        this.middleNum = generator.nextInt(10 - 8) + 8
        this.endNum = generator.nextInt(12 - 10) + 10
    }

    fun generateComedy() {
        this.headNum = generator.nextInt(14 - 12) + 12
        this.middleNum = generator.nextInt(16 - 14) + 14

        this.endNum = generator.nextInt(18 - 16) + 16
    }

    fun readFromRandom() {
        readFile(fileListPresenter.get(headNum))
        appendString(resultList)
        readFile(fileListPresenter.get(middleNum))
        appendString(resultList)
        readFile(fileListPresenter.get(endNum))
        appendString(resultList)
    }

    fun readFile(file: String) {
        resultList.clear()
        this.resultList = view.readFile(file)
    }

    fun appendString(list: ArrayList<String>) {
        list.forEach {
            resultText.append(it)
            resultText.append("\n")
        }

    }

    fun getResult():StringBuilder {
        return this.resultText
    }

}




