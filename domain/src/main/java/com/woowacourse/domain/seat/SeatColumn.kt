package com.woowacourse.domain.seat

@JvmInline
value class SeatColumn(val value: Int) {
    init {
        require(value >= VALID_MIN_VALUE) { "잘못된 값: $value $VALID_MIN_VALUE 이상의 값을 입력해주세요." }
    }

    companion object {
        private const val VALID_MIN_VALUE = 0
    }
}
