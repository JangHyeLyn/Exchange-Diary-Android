package com.km.exchangediary.ui.notification_page

class TimeConverter {
    enum class TimeValue(val value: Int,val maximum : Int, val msg : String) {
        SEC(60,60,"분 전"),
        MIN(60,24,"시간 전"),
        HOUR(24,30,"일 전"),
        DAY(30,12,"달 전"),
        MONTH(12,Int.MAX_VALUE,"년 전")
    }

    fun convertTime(createTime: Long): String? {
        val curTime = System.currentTimeMillis()
        var gapTime = (curTime - createTime) / 1000
        var msg: String? = null
        if (createTime < TimeValue.SEC.value)
            msg = "방금 전"
        else {
            for (i in TimeValue.values()) {
                gapTime /= i.value
                if (gapTime < i.maximum) {
                    msg = "${gapTime}${i.msg}"
                    break
                }
            }
        }
        return msg
    }
}