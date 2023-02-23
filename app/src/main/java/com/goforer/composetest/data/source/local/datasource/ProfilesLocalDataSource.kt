package com.goforer.composetest.data.source.local.datasource

import com.goforer.composetest.data.source.model.entity.source.profile.Profile
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfilesLocalDataSource @Inject constructor() {
    val profiles = mutableListOf(
        Profile(0,"LLyyiok", "남성", true),
        Profile(1,"Afredo", "남성", true),
        Profile(2,"Aliche", "여성", true),
        Profile(3,"Tina", "여성", true),
        Profile(4,"Lolly", "여성", true),
        Profile(5,"Hassen", "남성", true),
        Profile(6,"Lyll", "여성", true),
        Profile(7,"Kevin", "남성", true),
        Profile(8,"Tony", "남성", true),
        Profile(9,"Steven", "남성", false),
        Profile(10,"Micle", "남성", false),
        Profile(11,"Micell", "여성", true),
        Profile(12,"Lukoh", "남성", true),
        Profile(13,"Alex", "남성", false),
        Profile(14,"Alice", "여성", false),
        Profile(15,"Tana", "여성", false),
        Profile(16,"Lona", "여성", true),
        Profile(17,"Kovin", "남성", true),
        Profile(18,"Jully", "여성", true),
        Profile(19,"Kovak", "남성", true),
        Profile(20,"Tom", "남성", false),
        Profile(21,"Steven", "남성", false),
        Profile(22,"Max", "남성", false),
        Profile(23,"Mina", "여성", true),
        Profile(24,"Luke", "남성", true),
        Profile(25,"Luice", "남성", false),
        Profile(26,"Alleo", "여성", false),
        Profile(27,"Fyinia", "여성", false),
        Profile(28,"Rolly", "여성", true),
        Profile(29,"Kutic", "남성", true),
        Profile(30,"Molly", "여성", true),
        Profile(31,"Kornak", "남성", true),
        Profile(32,"Tomkus", "남성", false),
        Profile(33,"Stylen", "남성", false),
        Profile(34,"Bruice", "남성", false),
        Profile(35,"Lorn", "여성", true),
        Profile(36,"Jumy", "여성", true),
        Profile(37,"Tomson", "남성", false),
        Profile(38,"Stella", "du성", false),
        Profile(39,"Mic", "남성", false),
        Profile(40,"Dorothy", "여성", true)
    )
}