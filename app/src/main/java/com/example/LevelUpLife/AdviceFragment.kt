package com.example.LevelUpLife

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.LevelUpLife.LevelUp.api.Advice
import com.example.LevelUpLife.LevelUp.api.AdviceApi
import com.example.LevelUpLife.databinding.FragmentAdviceBinding
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


class AdviceFragment : Fragment() {

    private lateinit var binding : FragmentAdviceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAdviceBinding.inflate(layoutInflater,container,false)
        val view = binding.root

        // Get references to the components in the layout
        val btnSearchAdvice = binding.btnSearch
        val tvAdvice = binding.tvAdvise
        val etAdvice = binding.etAdvice




        btnSearchAdvice.setOnClickListener{

            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.adviceslip.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val query = etAdvice.text.toString()

            val randomAdvice = retrofit.create<AdviceApi>().getAdvice(query)

            randomAdvice.enqueue(object : Callback<Advice> {

                override fun onResponse(call: Call<Advice>, response: Response<Advice>) {

                    println("checking response")

                    if(response.isSuccessful){

                        println(response.body().toString())

                        val advice = response.body()

                        if(advice != null){
                            println(advice)

                            if (advice.slips.isNotEmpty()){
                                tvAdvice.text = advice.slips.toString()
                            } else{
                                tvAdvice.text = "advice not found"
                            }



                        }
                    }
                }

                override fun onFailure(call: Call<Advice>, t: Throwable) {
                    println(t.printStackTrace())
                }
            })

        }

        return view

    }


}