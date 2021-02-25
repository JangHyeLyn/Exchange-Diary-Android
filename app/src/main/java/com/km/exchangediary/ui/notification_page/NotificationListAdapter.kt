package com.km.exchangediary.ui.notification_page

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.km.exchangediary.R
import com.km.exchangediary.databinding.ItemNotificationBinding

class NotificationListAdapter : RecyclerView.Adapter<NotificationListAdapter.NotificationViewHolder>() {
    private val notificationList = arrayListOf<NotificationData>(
        NotificationData("","오늘 뭐했니","어서 일기를 작성해주세요!\n다른 멤버들이 기다리고 있어요\uD83E\uDD7A","평균 28세들","3시간 전"),
        NotificationData("","오늘 뭐했니","📔쭈피님에게 일기장이 넘어왔어요","평균 28세들","9시간 전"),
        NotificationData("","에그인헬","투표 만장일치로 일기장이 임의종료되었습니다.","돼지파티","2일 전"),
        NotificationData("","최대열네글자를쓸수있게하고자","'장발산역엘사'님이 탈퇴하셨습니다.","SIBAL","1주 전"),
        NotificationData("","에그인헬","‼️일기장 임의종료 투표에 참여해주세요.","돼지파티","2일 전"),
        NotificationData("","한","이용하지 않는 일기장이 종료되었습니다.","그룹명최대열글자까지","4주 전"),
        NotificationData("","한","⚠️1개월 간 작성된 일기가 없어 해당 일기장이 종료될 예정입니다. 종료를 원치 않으시면 3일 이내로 일기를 작성해주세요.","그룹명최대열글자까지","1개월 전"),
        NotificationData("","후라이","🥳일기장을 다 썼네요!\n이제 마지막 페이지에서 통계를 확인할 수 있어요!","전설의 레전드","1개월 전"),
        NotificationData("","오늘 뭐했니","새로운 일기장에 초대받았어요!\n'개똥이'님과 교환일기를 작성하러 가볼까요?~?","평균 28세들","9시간 전"),
        NotificationData("","후라이","다음 작성자 지정을 깜박하셨네요!\n누구에게 일기장을 넘겨줄까요?🤔","전설의 레전드","3주 전"),
        NotificationData("","오늘 뭐했니","✍️ 이제 일기 작성 가능!\n초대한 멤버가 들어와서 일기장을 넘겨줄 수 있어요 :)","평균 28세들","1개월 전")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder =
        NotificationViewHolder(DataBindingUtil.inflate(
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater,
            R.layout.item_notification,
            parent,
            false)
        )

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.onBind(notificationList[position])
    }

    override fun getItemCount(): Int = notificationList.size

    class NotificationViewHolder(private val binding: ItemNotificationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: NotificationData) {
            /* TODO Glide를 통해 다이어리 커버 이미지 바인딩 */
            binding.tvNotificationDiaryTitle.text = item.diaryTitle
            binding.tvNotificationDetail.text = item.notificationDetail
            binding.tvNotificationGroupNameAndTime.text = "${item.groupName} · ${item.notificationTime}"
        }
    }
}