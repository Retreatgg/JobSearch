<#import "../layot.ftlh" as main>
<@main.layout script="">

    <style>
        .role-selection {
            max-width: 400px;
            margin: 0 auto;
            background-color: #fff;
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
        }
        .role-selection a {
            display: block;
            margin-bottom: 15px;
            padding: 15px 20px;
            background-color: #007bff;
            color: #fff;
            text-align: center;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }
        .role-selection a:hover {
            background-color: #0056b3;
            color: #fff;
            text-decoration: none;
        }
    </style>

    <div class="role-selection mt-5">
        <h2 class="text-center mb-4">Choose your role</h2>
        <a href="/auth/register?role=employer">Company</a>
        <a href="/auth/register?role=applicant">Applicant</a>
    </div>

</@main.layout>