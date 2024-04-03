<#import "../layot.ftlh" as main>
<@main.layout>
    <form action="/users/register" method="post">
        <div class="form-group">
            <label for="name">Your name</label>
            <input type="text" class="form-control" id="name" name="name">
        </div>
        <div class="form-group">
            <label for="surname">Your surname</label>
            <input type="text" class="form-control" id="surname" name="surname">
        </div>
        <div class="form-group">
            <label for="age">Your age</label>
            <input type="number" class="form-control" id="age" name="age">
        </div>
        <div class="form-group">
            <label for="email">Your email</label>
            <input type="email" class="form-control" id="email" name="email">
        </div>
        <div class="form-group">
            <label for="password">Your password</label>
            <input type="password" class="form-control" id="password" name="password">
        </div>
        <div class="form-group">
            <label for="phoneNumber">Your phone number</label>
            <input type="text" class="form-control" id="phoneNumber" name="phoneNumber">
        </div>

            <select class="form-select" aria-label="Default select example" name="accountType">
                <option selected>Select your account type</option>
                <option value="APPLICANT">APPLICANT</option>
                <option value="EMPLOYER">EMPLOYER</option>
            </select>

        <button type="submit" class="btn btn-primary">Registration</button>
    </form>
</@main.layout>