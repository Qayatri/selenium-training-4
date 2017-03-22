package ru.stqa.training.selenium;

/*
1) регистрация новой учётной записи с достаточно уникальным адресом электронной почты (чтобы не конфликтовало с ранее созданными пользователями, в том числе при предыдущих запусках того же самого сценария),
2) выход (logout), потому что после успешной регистрации автоматически происходит вход,
3) повторный вход в только что созданную учётную запись,
4) и ещё раз выход.

В форме регистрации есть капча, её нужно отключить в админке учебного приложения на вкладке Settings -> Security.
 */
public class RegisterUser {
}
