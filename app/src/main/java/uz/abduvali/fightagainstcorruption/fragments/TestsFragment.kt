package uz.abduvali.fightagainstcorruption.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import uz.abduvali.fightagainstcorruption.R
import uz.abduvali.fightagainstcorruption.databinding.FragmentTestsBinding
import uz.abduvali.fightagainstcorruption.models.AnswerData
import uz.abduvali.fightagainstcorruption.models.TestData

class TestsFragment : Fragment(R.layout.fragment_tests) {

    private val binding by viewBinding(FragmentTestsBinding::bind)
    private lateinit var list: ArrayList<TestData>
    private var position = 0
    private var answers = 0
    private lateinit var test: TestData

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        loadUI()
        binding.apply {
            button.setOnClickListener {
                when (group.checkedRadioButtonId) {
                    R.id.tv1 -> {
                        check(0)
                    }
                    R.id.tv2 -> {
                        check(1)
                    }
                    R.id.tv3 -> {
                        check(2)
                    }
                    R.id.tv4 -> {
                        check(3)
                    }
                }
            }
        }
    }

    private fun loadUI() {
        if (position < list.size) {
            test = list[position]
            binding.apply {
                group.clearCheck()
                answersTv.text = "To'g'ri javoblar: " + answers
                question.text = "${position + 1}. ${test.question}"
                tv1.text = test.list[0].title
                tv2.text = test.list[1].title
                tv3.text = test.list[2].title
                tv4.text = test.list[3].title
            }
        } else {
            Toast.makeText(requireContext(), "Savol tugadi", Toast.LENGTH_SHORT).show()
        }
    }

    private fun check(index: Int) {
        if (test.list[index].isAnswer) {
            position++
            answers++
            loadUI()
        } else {
            position++
            loadUI()
        }
    }

    private fun initList(): List<TestData> {
        list = ArrayList()
        list.apply {
            for (i in 1..10) {
                add(
                    TestData(
                        "Korruptsiya nima?",
                        listOf(
                            AnswerData("pora berish", false),
                            AnswerData("pora olish", false),
                            AnswerData("vakolatni suiste'mol qilish", false),
                            AnswerData("barchasi to'g'ri", true)
                        ).shuffled()
                    )
                )
                add(
                    TestData(
                        " Korruptsiyaning oldini olish quyidagilardan iborat:",
                        listOf(
                            AnswerData(
                                "huquqni muhofaza qiluvchi organlar va davlat hokimiyati organlarining faoliyati",
                                false
                            ),
                            AnswerData(
                                "fuqarolik jamiyati institutlari, tashkilotlari va shaxslarning faoliyati " +
                                        "korruptsiya sabablarini aniqlash", false
                            ),
                            AnswerData(
                                " federal davlat hokimiyati organlari, organlar faoliyati \n" +
                                        "rossiya Federatsiyasining ta'sis sub'ektlarining davlat hokimiyati organlari, mahalliy hokimiyat organlari \n" +
                                        "o'zini o'zi boshqarish, fuqarolik jamiyati institutlari, tashkilotlar va shaxslar \n" +
                                        "o‘z vakolatlari doirasida korrupsiyaning oldini olish, shu jumladan aniqlash \n" +
                                        "va keyinchalik korruptsiya sabablarini bartaraf etish ",
                                true
                            ),
                            AnswerData(
                                "korruptsiya sabablarini aniqlash va keyinchalik bartaraf etish.",
                                false
                            )
                        ).shuffled()
                    )
                )
                add(
                    TestData(
                        "Davlat, munitsipal (ma'muriy) funktsiyalari. \n" +
                                "tashkilotni boshqarish - bu davlat yoki shahar hokimiyati \n" +
                                "majburiy qarorlar qabul qilish uchun xodim: ",
                        listOf(
                            AnswerData(
                                "kadrlar uchun, tashkiliy-texnik, moliyaviy, moddiy \n" +
                                        "ushbu tashkilot bilan bog'liq texnik yoki boshqa masalalar",
                                true
                            ),
                            AnswerData(
                                " ushbu tashkilotga nisbatan moliyaviy va moddiy masalalar bo'yicha ",
                                false
                            ),
                            AnswerData(
                                "muayyan ishlarni amalga oshirish uchun ruxsatnomalar (litsenziyalar) berish bilan bog'liq \n" +
                                        "ushbu tashkilotning faoliyat turi va (yoki) individual harakatlari ",
                                false
                            ),
                            AnswerData(
                                "shu munosabat bilan moliyaviy va moddiy-texnikaviy ta'minot masalalari bo'yicha \n" +
                                        "tashkilotlar, shu jumladan ruxsatnomalar (litsenziyalar) berish bilan bog‘liq yechimlar. \n" +
                                        "faoliyatning ma'lum bir turini va (yoki) buning individual harakatlarini amalga oshirish uchun \n" +
                                        "tashkil etish yoki bunday yechimlar loyihalarini tayyorlash.",
                                false
                            )
                        ).shuffled()
                    )
                )
            }
        }
        return list
    }
}