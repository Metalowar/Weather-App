### Склад учасників проєкту:
- Мартинюк Михайло
- Маркін Андрій
- Терещук Мирослава

### Короткий опис:
Застосунок для показу погоди (температури, опадів, швидкість вітру) в конкретному місті

#### Функціонал:
- Пошук міст
- Вивід списку міст
- Погода вибраного міста на даний момент часу
- Мін/макс температура за поточний день 
- Кількість опадів
- Прогноз на 7 днів (без деталізаіцї)
 
#### Застосунок матиме 2 екрани:
- Екран пошуку міст
- Екран деталей погоди по вибраному місті.

#### Technology Stack
1. **Мова програмування: Kotlin**
   - Ми обрали Kotlin як основну мову програмування для розробки додатку. Вона є сучасною, безпечною і потужною мовою, яка добре інтегрується з платформою Android.
2. **API: OpenWeather**
   - Для отримання актуальних даних про погоду ми використовуємо OpenWeather API. Це API забезпечує доступ до різноманітних погодних даних, таких як температура, опади, вітер тощо.
3. **Інтерфейс користувача: Android XML**
   - Дизайн інтерфейсу користувача створюється за допомогою Android XML. Це дозволяє нам створити зручний і інтуїтивно зрозумілий інтерфейс для користувачів вашого додатку.
4. **Бібліотеки та інструменти:**
   - **Retrofit:** Використовується для роботи з HTTP-запитами та взаємодії з OpenWeather API.
   - **Gson:** Бібліотека для парсингу JSON-даних, отриманих від OpenWeather API.
   - **ViewModel і LiveData (Android Architecture Components):** Допомагають у підтримці життєвого циклу та управлінні даними у нашому додатку.
5. **Розробка та збірка:**
   - Використовуємо Android Studio як основне середовище розробки.
   - Gradle для управління залежностями та складання проекту.


#### Використано Api для пошуку міст латинецею, від 3 символів
- http://geodb-cities-api.wirefreethought.com/docs/guides/getting-started/integration/java

#### Використано Api для отримання погоди по назві міста
- https://openweathermap.org/api/one-call-3
- Апі зареєстровано на Андрій Маркін, кількість запитів обмежена (1000 в день)