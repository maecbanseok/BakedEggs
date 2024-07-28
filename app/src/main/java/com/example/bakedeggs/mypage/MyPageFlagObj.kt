package com.example.bakedeggs.mypage

object MyPageFlagObj {
    private var myPageFlag: MyPageFlag = MyPageFlag()


    fun switchSnsOpen() {

        if (myPageFlag.isOpenSNS) {
            myPageFlag = MyPageFlag(
                false,
                myPageFlag.isOpenFavorite,
                myPageFlag.isOpenBlock
            )
        } else {
            myPageFlag = MyPageFlag(
                true,
                myPageFlag.isOpenFavorite,
                myPageFlag.isOpenBlock
            )
        }
    }

    fun switchFavoriteOpen() {

        if (myPageFlag.isOpenFavorite) {
            myPageFlag = MyPageFlag(
                myPageFlag.isOpenSNS,
                false,
                myPageFlag.isOpenBlock
            )
        } else {
            myPageFlag = MyPageFlag(
                myPageFlag.isOpenSNS,
                true,
                myPageFlag.isOpenBlock
            )
        }

    }

    fun switchBlockOpen() {

        if (myPageFlag.isOpenBlock) {
            myPageFlag = MyPageFlag(
                myPageFlag.isOpenSNS,
                myPageFlag.isOpenFavorite,
                false
            )
        } else {
            myPageFlag = MyPageFlag(
                myPageFlag.isOpenSNS,
                myPageFlag.isOpenFavorite,
                true
            )
        }
    }

    fun getFlag() = myPageFlag
}