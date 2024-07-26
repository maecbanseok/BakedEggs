package com.example.bakedeggs.mypage

object MyPageFlagObj {
    private lateinit var myPageFlag: MyPageFlag

    fun initData() {
        myPageFlag = MyPageFlag()
    }

    fun switchSnsOpen() {
        if(!this::myPageFlag.isInitialized) initData()
        if(myPageFlag.isOpenSNS) {
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
        if(!this::myPageFlag.isInitialized) initData()
        if(myPageFlag.isOpenFavorite) {
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
        if(!this::myPageFlag.isInitialized) initData()
        if(myPageFlag.isOpenBlock) {
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