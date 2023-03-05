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
                Notification(0,"LLyyiok", "Development", "Android","https://avatars.githubusercontent.com/u/18302717?v=4", "Coding Rules", "This is a notification sample for development and anyone can develop the app easily with the this advanced architecture."),
                Notification(1,"Afredo", "Marketing", "Marketing","https://avatars.githubusercontent.com/u/18302717?v=4", "Marketing Policy", "This is a notification sample for development and anyone can develop the app easily with the this advanced architecture."),
                Notification(2,"Aliche", "Development", "Android","https://avatars.githubusercontent.com/u/18302717?v=4", "Architecture Blueprint", "This is a notification sample for development and anyone can develop the app easily with the this advanced architecture."),
                Notification(3,"Tina", "Sales", "Domestic Sales 1","https://avatars.githubusercontent.com/u/18302717?v=4","Vehicle allocation notice", "This is a notification sample for development and anyone can develop the app easily with the this advanced architecture"),
                Notification(4,"Lolly", "Support", "HR","https://avatars.githubusercontent.com/u/18302717?v=4","New personnel appointments", "This is a notification sample for development and anyone can develop the app easily with the this advanced architecture"),
                Notification(5,"Hassen", "Support", "People 1","https://avatars.githubusercontent.com/u/18302717?v=4","Notice of consultation", "This is a notification sample for development and anyone can develop the app easily with the this advanced architecture"),
                Notification(6,"Lyll", "Support", "People 2","https://avatars.githubusercontent.com/u/18302717?v=4", "Relations", "This is a notification sample for development and anyone can develop the app easily with the this advanced architecture"),
                Notification(7,"Kevin", "Sales", "Domestic Sales 2","https://avatars.githubusercontent.com/u/18302717?v=4", "Sales Policy", "This is a notification sample for development and anyone can develop the app easily with the this advanced architecture"),
                Notification(8,"Tony", "Sales", "Domestic Sales 3","https://avatars.githubusercontent.com/u/18302717?v=4", "Sales Policy", "This is a notification sample for development and anyone can develop the app easily with the this advanced architecture"),
                Notification(9,"Steven", "Sales", "International Sales 1","https://avatars.githubusercontent.com/u/18302717?v=4", "Sales Policy", "This is a notification sample for development and anyone can develop the app easily with the this advanced architecture"),
                Notification(10,"Micle", "Marketing", "Domestic Marketing 1","https://avatars.githubusercontent.com/u/18302717?v=4", "Monthly Report for Marketing Meeting", "This is a notification sample for development and anyone can develop the app easily with the this advanced architecture"),
                Notification(11,"Micell", "Marketing", "Domestic Marketing 2","https://avatars.githubusercontent.com/u/18302717?v=4", "Weekly Report for Marketing Meeting", "This is a notification sample for development and anyone can develop the app easily with the this advanced architecture"),
                Notification(12,"Lukoh", "Development", "Domestic Sales 1","https://avatars.githubusercontent.com/u/18302717?v=4", "Condition of domestic market ", "This is a notification sample for development and anyone can develop the app easily with the this advanced architecture"),
                Notification(13,"Alex", "Development", "iOS","https://avatars.githubusercontent.com/u/18302717?v=4", "Coding Rules", "+820101111-1111"),
                Notification(14,"Alice", "Development", "BackEnd","https://avatars.githubusercontent.com/u/18302717?v=4", "Micro Architecture Policy", "This is a notification sample for development and anyone can develop the app easily with the this advanced architecture"),
                Notification(15,"Tana", "Development", "Data 1","https://avatars.githubusercontent.com/u/18302717?v=4", "Data Capturing Rules", "This is a notification sample for development and anyone can develop the app easily with the this advanced architecture"),
                Notification(16,"Lona", "Development", "Data 2","https://avatars.githubusercontent.com/u/18302717?v=4", "Data Structure Notice", "This is a notification sample for development and anyone can develop the app easily with the this advanced architecture"),
                Notification(17,"Kovin", "Development", "AI","https://avatars.githubusercontent.com/u/18302717?v=4", "Notice of Seminar", "This is a notification sample for development and anyone can develop the app easily with the this advanced architecture"),
                Notification(18,"Jully", "Development", "Android","https://avatars.githubusercontent.com/u/18302717?v=4", "Compose Migration Rules", "This is a notification sample for development and anyone can develop the app easily with the this advanced architecture"),
                Notification(19,"Kovak", "Development", "Infra","https://avatars.githubusercontent.com/u/18302717?v=4", "Infra Structure", "+This is a notification sample for development and anyone can develop the app easily with the this advanced architecture"),
                Notification(20,"Tom", "Support", "General Affairs","https://avatars.githubusercontent.com/u/18302717?v=4", "Notice of Clean Office", "This is a notification sample for development and anyone can develop the app easily with the this advanced architecture"),
                Notification(21,"Steven", "Support", "HR","https://avatars.githubusercontent.com/u/18302717?v=4", "Resource Staffing", "+820101111-1111"),
                Notification(22,"Max", "Development", "Domestic Sales 1","https://avatars.githubusercontent.com/u/18302717?v=4", "Monthly Report for Sales Meeting", "This is a notification sample for development and anyone can develop the app easily with the this advanced architecture"),
                Notification(23,"Mina", "Development", "Domestic Sales 1","https://avatars.githubusercontent.com/u/18302717?v=4", "Weekly Report for Sales Meeting", "This is a notification sample for development and anyone can develop the app easily with the this advanced architecture"),
                Notification(24,"Luke", "Development", "Domestic Sales 1","https://avatars.githubusercontent.com/u/18302717?v=4", "Daily Report for Sales Meeting", "This is a notification sample for development and anyone can develop the app easily with the this advanced architecture"),
                Notification(25,"Luice", "Support", "General Affairs","https://avatars.githubusercontent.com/u/18302717?v=4", "Notice of Clean", "This is a notification sample for development and anyone can develop the app easily with the this advanced architecture"),
            )
        )
    }
}