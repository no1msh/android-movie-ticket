package woowacourse.movie.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.woowacourse.domain.TicketBundle
import woowacourse.movie.BundleKeys.MOVIE_BOOKING_INFO_KEY
import woowacourse.movie.MovieBookingInfo
import woowacourse.movie.R
import woowacourse.movie.getSerializableCompat

class BookCompleteActivity : BackButtonActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_complete)

        val movieBookingData = getMovieBookingInfo()
        displayToastIfDummyData(movieBookingData)
        initView(movieBookingData)
    }

    private fun getMovieBookingInfo(): MovieBookingInfo {
        return intent.getSerializableCompat(MOVIE_BOOKING_INFO_KEY)
            ?: MovieBookingInfo.dummyData
    }

    private fun displayToastIfDummyData(movieBookingData: MovieBookingInfo) {
        if (movieBookingData == MovieBookingInfo.dummyData) {
            Toast.makeText(
                this,
                getString(R.string.cant_get_movie_booking_data),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun initView(movieBookingData: MovieBookingInfo) {
        findViewById<TextView>(R.id.tv_book_movie_title).text = movieBookingData.movieInfo.title
        findViewById<TextView>(R.id.tv_book_date).text =
            movieBookingData.formatBookingTime()
        findViewById<TextView>(R.id.tv_book_person_count).text =
            getString(R.string.book_person_count).format(movieBookingData.ticketCount)
        findViewById<TextView>(R.id.tv_book_total_pay).text =
            getString(R.string.book_total_pay).format(
                TicketBundle.create(movieBookingData.ticketCount).calculateTotalPrice(
                    movieBookingData.date,
                    movieBookingData.time
                )
            )
    }

    companion object {
        fun intent(context: Context) = Intent(context, BookCompleteActivity::class.java)
    }
}