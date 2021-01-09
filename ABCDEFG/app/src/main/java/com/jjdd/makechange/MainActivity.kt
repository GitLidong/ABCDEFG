package com.jjdd.makechange

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.children
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        val listView: RecyclerView = findViewById(R.id.main_list)
        val decoration = MainDecoration(this)
        val adapter = MainAdapter(this)
        listView.addItemDecoration(decoration)
        listView.layoutManager = LinearLayoutManager(this)
        listView.adapter = adapter
    }

    class MainDecoration(private val ctx: Context) :
        RecyclerView.ItemDecoration() {

        private val mPaint: Paint = Paint()
        private val mPaintBlue: Paint = Paint()

        init {
            mPaint.color = Color.RED
            mPaintBlue.color = Color.BLUE
        }

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            val itemPosition = parent.getChildAdapterPosition(view)
            if (itemPosition < parent.childCount - 1) {
                outRect.set(10, 0, 10, 10);
            }
        }

        override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            super.onDraw(c, parent, state)

            val childCount = parent.childCount
            for (index in 0 until childCount - 1) {
                val child = parent.getChildAt(index)

                val left = parent.paddingLeft + 10f
                val top = child.bottom.toFloat()
                val right = parent.width - 10f
                val bottom = top + 10f

                c.drawRect(left, top, right, bottom, mPaint)

                if (index == 0) {
                    val left1 = parent.paddingLeft.toFloat()
                    val top1 = child.top.toFloat()
                    val right1 = left1 + 10f
                    val bottom1 = child.bottom.toFloat()
                    c.drawRect(left1, top1, right1, bottom1, mPaintBlue)
                }
            }

        }
    }

    class MainAdapter(private val ctx: Context) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
            return ViewHolder(
                LayoutInflater.from(ctx).inflate(R.layout.item_info_view, parent, false)
            )
        }

        override fun getItemCount(): Int {
            return ActivityConfiguration.activityInfoList.size
        }

        override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
            val info = ActivityConfiguration.activityInfoList[position];
            holder.title.text = info.name
            holder.title.setOnClickListener {
                val intent = Intent(info.action)
                intent.setPackage(ctx.packageName)
                ctx.startActivity(intent)
            }
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val title: TextView = itemView.findViewById(android.R.id.title)
        }

    }

    class ActivityConfiguration {
        companion object {
            val activityInfoList = mutableListOf<ActivityInfo>()

            init {
                activityInfoList.add(ActivityInfo("RxJava", "com.jjdd.makechange.daily"))
                activityInfoList.add(ActivityInfo("RxJava", "com.jjdd.makechange.daily"))
                activityInfoList.add(ActivityInfo("RxJava", "com.jjdd.makechange.daily"))
                activityInfoList.add(ActivityInfo("RxJava", "com.jjdd.makechange.daily"))
            }
        }
    }

    data class ActivityInfo(val name: String, val action: String)

}