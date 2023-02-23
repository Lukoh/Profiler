/*
 * Copyright (C) 2021 The Android Open Source Project by Lukoh Nam, goForer
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License,
 * or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 */

package com.goforer.profiler.data.source.local

import com.goforer.profiler.data.source.model.entity.source.profile.Profile
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfilesLocalDataSource @Inject constructor() {
    val profiles = flow {
        emit(
            mutableListOf(
                Profile(0,"LLyyiok", "남성", true,"https://avatars.githubusercontent.com/u/18302717?v=4"),
                Profile(1,"Afredo", "남성", true,"https://avatars.githubusercontent.com/u/18302717?v=4"),
                Profile(2,"Aliche", "여성", true,"https://avatars.githubusercontent.com/u/18302717?v=4"),
                Profile(3,"Tina", "여성", true,"https://avatars.githubusercontent.com/u/18302717?v=4"),
                Profile(4,"Lolly", "여성", true,"https://avatars.githubusercontent.com/u/18302717?v=4"),
                Profile(5,"Hassen", "남성", true,"https://avatars.githubusercontent.com/u/18302717?v=4"),
                Profile(6,"Lyll", "여성", true,"https://avatars.githubusercontent.com/u/18302717?v=4"),
                Profile(7,"Kevin", "남성", true,"https://avatars.githubusercontent.com/u/18302717?v=4"),
                Profile(8,"Tony", "남성", true,"https://avatars.githubusercontent.com/u/18302717?v=4"),
                Profile(9,"Steven", "남성", false,"https://avatars.githubusercontent.com/u/18302717?v=4"),
                Profile(10,"Micle", "남성", false,"https://avatars.githubusercontent.com/u/18302717?v=4"),
                Profile(11,"Micell", "여성", true,"https://avatars.githubusercontent.com/u/18302717?v=4"),
                Profile(12,"Lukoh", "남성", true,"https://avatars.githubusercontent.com/u/18302717?v=4"),
                Profile(13,"Alex", "남성", false,"https://avatars.githubusercontent.com/u/18302717?v=4"),
                Profile(14,"Alice", "여성", false,"https://avatars.githubusercontent.com/u/18302717?v=4"),
                Profile(15,"Tana", "여성", false,"https://avatars.githubusercontent.com/u/18302717?v=4"),
                Profile(16,"Lona", "여성", true,"https://avatars.githubusercontent.com/u/18302717?v=4"),
                Profile(17,"Kovin", "남성", true,"https://avatars.githubusercontent.com/u/18302717?v=4"),
                Profile(18,"Jully", "여성", true,"https://avatars.githubusercontent.com/u/18302717?v=4"),
                Profile(19,"Kovak", "남성", true,"https://avatars.githubusercontent.com/u/18302717?v=4"),
                Profile(20,"Tom", "남성", false,"https://avatars.githubusercontent.com/u/18302717?v=4"),
                Profile(21,"Steven", "남성", false,"https://avatars.githubusercontent.com/u/18302717?v=4"),
                Profile(22,"Max", "남성", false,"https://avatars.githubusercontent.com/u/18302717?v=4"),
                Profile(23,"Mina", "여성", true,"https://avatars.githubusercontent.com/u/18302717?v=4"),
                Profile(24,"Luke", "남성", true,"https://avatars.githubusercontent.com/u/18302717?v=4"),
                Profile(25,"Luice", "남성", false,"https://avatars.githubusercontent.com/u/18302717?v=4"),
                Profile(26,"Alleo", "여성", false,"https://avatars.githubusercontent.com/u/18302717?v=4"),
                Profile(27,"Fyinia", "여성", false,"https://avatars.githubusercontent.com/u/18302717?v=4"),
                Profile(28,"Rolly", "여성", true,"https://avatars.githubusercontent.com/u/18302717?v=4"),
                Profile(29,"Kutic", "남성", true,"https://avatars.githubusercontent.com/u/18302717?v=4"),
                Profile(30,"Molly", "여성", true,"https://avatars.githubusercontent.com/u/18302717?v=4"),
                Profile(31,"Kornak", "남성", true,"https://avatars.githubusercontent.com/u/18302717?v=4"),
                Profile(32,"Tomkus", "남성", false,"https://avatars.githubusercontent.com/u/18302717?v=4"),
                Profile(33,"Stylen", "남성", false,"https://avatars.githubusercontent.com/u/18302717?v=4"),
                Profile(34,"Bruice", "남성", false,"https://avatars.githubusercontent.com/u/18302717?v=4"),
                Profile(35,"Lorn", "여성", true,"https://avatars.githubusercontent.com/u/18302717?v=4"),
                Profile(36,"Jumy", "여성", true,"https://avatars.githubusercontent.com/u/18302717?v=4"),
                Profile(37,"Tomson", "남성", false,"https://avatars.githubusercontent.com/u/18302717?v=4"),
                Profile(38,"Stella", "du성", false,"https://avatars.githubusercontent.com/u/18302717?v=4"),
                Profile(39,"Mic", "남성", false,"https://avatars.githubusercontent.com/u/18302717?v=4"),
                Profile(40,"Dorothy", "여성", true,"https://avatars.githubusercontent.com/u/18302717?v=4")
            )
        )
    }
}