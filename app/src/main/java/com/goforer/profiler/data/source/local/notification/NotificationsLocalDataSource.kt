/*
 * Copyright (C) 2023 The Android Open Source Project by Lukoh Nam, goForer
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

package com.goforer.profiler.data.source.local.notification

import com.goforer.profiler.data.source.model.entity.source.response.notification.Notification
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationsLocalDataSource @Inject constructor() {
    val notifications = flow {
        emit(
            mutableListOf(
                Notification(0,"LLyyiok", "Development", "Android","https://avatars.githubusercontent.com/u/18302717?v=4", "Coding Rules",0,"Scouts & Guides Participation Needed\n1st March, 2023 \n\n This is a notification sample for development and anyone can develop the app easily with the this advanced architecture.Our school has decided to send a troop of scouts and guides to the jamboree to be held at Lucknow from the 20th to the 27th of October. Those scouts and guides interested to participate in the jamboree may give their names to the undersigned by the 7th of October.\n\n\nTeam Manager"),
                Notification(1,"Afredo", "Marketing", "Marketing","https://avatars.githubusercontent.com/u/18302717?v=4", "Marketing Policy",1,"Scouts & Guides Participation Needed\n1st March, 2023 \n\n This is a notification sample for development and anyone can develop the app easily with the this advanced architecture.Our school has decided to send a troop of scouts and guides to the jamboree to be held at Lucknow from the 20th to the 27th of October. Those scouts and guides interested to participate in the jamboree may give their names to the undersigned by the 7th of October.\n\n\nTeam Manager"),
                Notification(2,"Aliche", "Development", "Android","https://avatars.githubusercontent.com/u/18302717?v=4", "Architecture Blueprint",2,"Scouts & Guides Participation Needed\n1st March, 2023 \n\n This is a notification sample for development and anyone can develop the app easily with the this advanced architecture.Our school has decided to send a troop of scouts and guides to the jamboree to be held at Lucknow from the 20th to the 27th of October. Those scouts and guides interested to participate in the jamboree may give their names to the undersigned by the 7th of October.\n\n\nTeam Manager"),
                Notification(3,"Tina", "Sales", "Domestic Sales 1","https://avatars.githubusercontent.com/u/18302717?v=4","Vehicle allocation notice",3,"Scouts & Guides Participation Needed\n1st March, 2023 \n\n This is a notification sample for development and anyone can develop the app easily with the this advanced architecture.Our school has decided to send a troop of scouts and guides to the jamboree to be held at Lucknow from the 20th to the 27th of October. Those scouts and guides interested to participate in the jamboree may give their names to the undersigned by the 7th of October.\n\n\nTeam Manager"),
                Notification(4,"Lolly", "Support", "HR","https://avatars.githubusercontent.com/u/18302717?v=4","New personnel appointments",1, "Scouts & Guides Participation Needed\n1st March, 2023 \n\n This is a notification sample for development and anyone can develop the app easily with the this advanced architecture.Our school has decided to send a troop of scouts and guides to the jamboree to be held at Lucknow from the 20th to the 27th of October. Those scouts and guides interested to participate in the jamboree may give their names to the undersigned by the 7th of October.\n\n\nTeam Manager"),
                Notification(5,"Hassen", "Support", "People 1","https://avatars.githubusercontent.com/u/18302717?v=4","Notice of consultation",0, "Scouts & Guides Participation Needed\n1st March, 2023 \n\n This is a notification sample for development and anyone can develop the app easily with the this advanced architecture.Our school has decided to send a troop of scouts and guides to the jamboree to be held at Lucknow from the 20th to the 27th of October. Those scouts and guides interested to participate in the jamboree may give their names to the undersigned by the 7th of October.\n\n\nTeam Manager"),
                Notification(6,"Lyll", "Support", "People 2","https://avatars.githubusercontent.com/u/18302717?v=4", "Relations",3, "Scouts & Guides Participation Needed\n1st March, 2023 \n\n This is a notification sample for development and anyone can develop the app easily with the this advanced architecture.Our school has decided to send a troop of scouts and guides to the jamboree to be held at Lucknow from the 20th to the 27th of October. Those scouts and guides interested to participate in the jamboree may give their names to the undersigned by the 7th of October.\n\n\nTeam Manager"),
                Notification(7,"Kevin", "Sales", "Domestic Sales 2","https://avatars.githubusercontent.com/u/18302717?v=4", "Sales Policy",2, "Scouts & Guides Participation Needed\n1st March, 2023 \n\n This is a notification sample for development and anyone can develop the app easily with the this advanced architecture.Our school has decided to send a troop of scouts and guides to the jamboree to be held at Lucknow from the 20th to the 27th of October. Those scouts and guides interested to participate in the jamboree may give their names to the undersigned by the 7th of October.\n\n\nTeam Manager"),
                Notification(8,"Tony", "Sales", "Domestic Sales 3","https://avatars.githubusercontent.com/u/18302717?v=4", "Sales Policy",0, "Scouts & Guides Participation Needed\n1st March, 2023 \n\n This is a notification sample for development and anyone can develop the app easily with the this advanced architecture.Our school has decided to send a troop of scouts and guides to the jamboree to be held at Lucknow from the 20th to the 27th of October. Those scouts and guides interested to participate in the jamboree may give their names to the undersigned by the 7th of October.\n\n\nTeam Manager"),
                Notification(9,"Steven", "Sales", "International Sales 1","https://avatars.githubusercontent.com/u/18302717?v=4", "Sales Policy",1, "Scouts & Guides Participation Needed\n1st March, 2023 \n\n This is a notification sample for development and anyone can develop the app easily with the this advanced architecture.Our school has decided to send a troop of scouts and guides to the jamboree to be held at Lucknow from the 20th to the 27th of October. Those scouts and guides interested to participate in the jamboree may give their names to the undersigned by the 7th of October.\n\n\nTeam Manager"),
                Notification(10,"Micle", "Marketing", "Domestic Marketing 1","https://avatars.githubusercontent.com/u/18302717?v=4", "Monthly Report for Marketing Meeting",2, "Scouts & Guides Participation Needed\n1st March, 2023 \n\n This is a notification sample for development and anyone can develop the app easily with the this advanced architecture.Our school has decided to send a troop of scouts and guides to the jamboree to be held at Lucknow from the 20th to the 27th of October. Those scouts and guides interested to participate in the jamboree may give their names to the undersigned by the 7th of October.\n\n\nTeam Manager"),
                Notification(11,"Micell", "Marketing", "Domestic Marketing 2","https://avatars.githubusercontent.com/u/18302717?v=4", "Weekly Report for Marketing Meeting",3, "Scouts & Guides Participation Needed\n1st March, 2023 \n\n This is a notification sample for development and anyone can develop the app easily with the this advanced architecture.Our school has decided to send a troop of scouts and guides to the jamboree to be held at Lucknow from the 20th to the 27th of October. Those scouts and guides interested to participate in the jamboree may give their names to the undersigned by the 7th of October.\n\n\nTeam Manager"),
                Notification(12,"Lukoh", "Development", "Domestic Sales 1","https://avatars.githubusercontent.com/u/18302717?v=4", "Condition of domestic market ",1, "Scouts & Guides Participation Needed\n1st March, 2023 \n\n This is a notification sample for development and anyone can develop the app easily with the this advanced architecture.Our school has decided to send a troop of scouts and guides to the jamboree to be held at Lucknow from the 20th to the 27th of October. Those scouts and guides interested to participate in the jamboree may give their names to the undersigned by the 7th of October.\n\n\nTeam Manager"),
                Notification(13,"Alex", "Development", "iOS","https://avatars.githubusercontent.com/u/18302717?v=4", "Coding Rules",3, "Scouts & Guides Participation Needed\n1st March, 2023 \n\n This is a notification sample for development and anyone can develop the app easily with the this advanced architecture.Our school has decided to send a troop of scouts and guides to the jamboree to be held at Lucknow from the 20th to the 27th of October. Those scouts and guides interested to participate in the jamboree may give their names to the undersigned by the 7th of October.\n\n\nTeam Manager"),
                Notification(14,"Alice", "Development", "BackEnd","https://avatars.githubusercontent.com/u/18302717?v=4", "Micro Architecture Policy",2, "Scouts & Guides Participation Needed\n1st March, 2023 \n\n This is a notification sample for development and anyone can develop the app easily with the this advanced architecture.Our school has decided to send a troop of scouts and guides to the jamboree to be held at Lucknow from the 20th to the 27th of October. Those scouts and guides interested to participate in the jamboree may give their names to the undersigned by the 7th of October.\n\n\nTeam Manager"),
                Notification(15,"Tana", "Development", "Data 1","https://avatars.githubusercontent.com/u/18302717?v=4", "Data Capturing Rules",1, "Scouts & Guides Participation Needed\n1st March, 2023 \n\n This is a notification sample for development and anyone can develop the app easily with the this advanced architecture.Our school has decided to send a troop of scouts and guides to the jamboree to be held at Lucknow from the 20th to the 27th of October. Those scouts and guides interested to participate in the jamboree may give their names to the undersigned by the 7th of October.\n\n\nTeam Manager"),
                Notification(16,"Lona", "Development", "Data 2","https://avatars.githubusercontent.com/u/18302717?v=4", "Data Structure Notice",2, "Scouts & Guides Participation Needed\n1st March, 2023 \n\n This is a notification sample for development and anyone can develop the app easily with the this advanced architecture.Our school has decided to send a troop of scouts and guides to the jamboree to be held at Lucknow from the 20th to the 27th of October. Those scouts and guides interested to participate in the jamboree may give their names to the undersigned by the 7th of October.\n\n\nTeam Manager"),
                Notification(17,"Kovin", "Development", "AI","https://avatars.githubusercontent.com/u/18302717?v=4", "Notice of Seminar",3, "Scouts & Guides Participation Needed\n1st March, 2023 \n\n This is a notification sample for development and anyone can develop the app easily with the this advanced architecture.Our school has decided to send a troop of scouts and guides to the jamboree to be held at Lucknow from the 20th to the 27th of October. Those scouts and guides interested to participate in the jamboree may give their names to the undersigned by the 7th of October.\n\n\nTeam Manager"),
                Notification(18,"Jully", "Development", "Android","https://avatars.githubusercontent.com/u/18302717?v=4", "Compose Migration Rules",1, "Scouts & Guides Participation Needed\n1st March, 2023 \n\n This is a notification sample for development and anyone can develop the app easily with the this advanced architecture.Our school has decided to send a troop of scouts and guides to the jamboree to be held at Lucknow from the 20th to the 27th of October. Those scouts and guides interested to participate in the jamboree may give their names to the undersigned by the 7th of October.\n\n\nTeam Manager"),
                Notification(19,"Kovak", "Development", "Infra","https://avatars.githubusercontent.com/u/18302717?v=4", "Infra Structure",0, "Scouts & Guides Participation Needed\n1st March, 2023 \n\n This is a notification sample for development and anyone can develop the app easily with the this advanced architecture.Our school has decided to send a troop of scouts and guides to the jamboree to be held at Lucknow from the 20th to the 27th of October. Those scouts and guides interested to participate in the jamboree may give their names to the undersigned by the 7th of October.\n\n\nTeam Manager"),
                Notification(20,"Tom", "Support", "General Affairs","https://avatars.githubusercontent.com/u/18302717?v=4", "Notice of Clean Office",3, "Scouts & Guides Participation Needed\n1st March, 2023 \n\n This is a notification sample for development and anyone can develop the app easily with the this advanced architecture.Our school has decided to send a troop of scouts and guides to the jamboree to be held at Lucknow from the 20th to the 27th of October. Those scouts and guides interested to participate in the jamboree may give their names to the undersigned by the 7th of October.\n\n\nTeam Manager"),
                Notification(21,"Steven", "Support", "HR","https://avatars.githubusercontent.com/u/18302717?v=4", "Resource Staffing",1, "Scouts & Guides Participation Needed\n1st March, 2023 \n\n This is a notification sample for development and anyone can develop the app easily with the this advanced architecture.Our school has decided to send a troop of scouts and guides to the jamboree to be held at Lucknow from the 20th to the 27th of October. Those scouts and guides interested to participate in the jamboree may give their names to the undersigned by the 7th of October.\n\n\nTeam Manager"),
                Notification(22,"Max", "Development", "Domestic Sales 1","https://avatars.githubusercontent.com/u/18302717?v=4", "Monthly Report for Sales Meeting",2, "Scouts & Guides Participation Needed\n1st March, 2023 \n\n This is a notification sample for development and anyone can develop the app easily with the this advanced architecture.Our school has decided to send a troop of scouts and guides to the jamboree to be held at Lucknow from the 20th to the 27th of October. Those scouts and guides interested to participate in the jamboree may give their names to the undersigned by the 7th of October.\n\n\nTeam Manager"),
                Notification(23,"Mina", "Development", "Domestic Sales 1","https://avatars.githubusercontent.com/u/18302717?v=4", "Weekly Report for Sales Meeting",3, "Scouts & Guides Participation Needed\n1st March, 2023 \n\n This is a notification sample for development and anyone can develop the app easily with the this advanced architecture.Our school has decided to send a troop of scouts and guides to the jamboree to be held at Lucknow from the 20th to the 27th of October. Those scouts and guides interested to participate in the jamboree may give their names to the undersigned by the 7th of October.\n\n\nTeam Manager"),
                Notification(24,"Luke", "Development", "Domestic Sales 1","https://avatars.githubusercontent.com/u/18302717?v=4", "Daily Report for Sales Meeting",0, "Scouts & Guides Participation Needed\n1st March, 2023 \n\n This is a notification sample for development and anyone can develop the app easily with the this advanced architecture.Our school has decided to send a troop of scouts and guides to the jamboree to be held at Lucknow from the 20th to the 27th of October. Those scouts and guides interested to participate in the jamboree may give their names to the undersigned by the 7th of October.\n\n\nTeam Manager"),
                Notification(25,"Luice", "Support", "General Affairs","https://avatars.githubusercontent.com/u/18302717?v=4", "Notice of Clean",1, "Scouts & Guides Participation Needed\n1st March, 2023 \n\n This is a notification sample for development and anyone can develop the app easily with the this advanced architecture.Our school has decided to send a troop of scouts and guides to the jamboree to be held at Lucknow from the 20th to the 27th of October. Those scouts and guides interested to participate in the jamboree may give their names to the undersigned by the 7th of October.\n\n\nTeam Manager"),
            )
        )
    }
}