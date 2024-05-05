<#import "../layot.ftlh" as main>
<@main.layout script="">
    <section class="vh-100 mt-5">
        <div class="container-fluid h-custom mt-5">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-md-9 col-lg-6 col-xl-5">
                    <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.webp"
                         class="img-fluid" alt="Sample image">
                </div>
                <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
                    <form class="mt-5" id="login-form" action="/auth/login" method="post">

                        <div data-mdb-input-init class="form-outline mb-4">
                            <input type="email" id="form3Example3" class="form-control form-control-lg"
                                   placeholder="Enter a valid email address" name="username"/>
                            <label class="form-label" for="form3Example3">Email address</label>
                        </div>

                        <div data-mdb-input-init class="form-outline mb-3">
                            <input type="password" id="form3Example4" class="form-control form-control-lg"
                                   placeholder="Enter password" name="password" autocomplete="on"/>
                            <label class="form-label" for="form3Example4">Password</label>
                        </div>

                        <div class="text-center text-lg-start mt-4 pt-2">
                            <button id="btn" type="submit" data-mdb-button-init data-mdb-ripple-init class="btn btn-primary btn-lg"
                                    style="padding-left: 2.5rem; padding-right: 2.5rem;">Login</button>
                            <p class="small fw-bold mt-2 pt-1 mb-0">Don't have an account? <a href="/users/register" class="link-danger">Register</a></p>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>


    <script>
        let form = document.getElementById('login-form')

        // form.addEventListener('submit', onLoginHandler)

        function onLoginHandler(event) {
            event.preventDefault()

            let target = event.target
            let data = new FormData(target)
            let user = Object.fromEntries(data)

            let headers = new Headers()
            headers.set('Authorization', 'Basic ' + btoa(user.email + ':' + user.password))

            fetchAuthorized(headers)
            // let userJson = JSON.stringify(user)
            // localStorage.setItem('user', userJson)
            // window.location.href = 'http://localhost:8089/'

        }

        async function fetchAuthorized(auth) {
            try {
                await makeRequest('http://localhost:8089/api/auth/login', updateOptions({
                    method: 'post',
                    headers: auth
                }));
            } catch (e) {
                alert(e)
            }
        }

        // btoa()
        // atob()

        function makeHeaders() {
            let user = restoreUser()
            // console.log(user);

            let headers = new Headers()
            headers.set('Content-Type', 'application/json')
            if (user) {
                headers.set('Authorization', 'Basic ' + btoa(user.email + ':' + user.password))
            }
            return headers
        }

        async function makeRequest(url, options) {
            let settings = options || requestSettings
            let response = await fetch(url, settings)

            if (response.ok) {
                return await response.json()
            } else {
                let err = new Error(response.status.toString())
                err.response = response
                console.log(err);
                throw err
            }
        }

        function updateOptions(options) {
            console.log(options);
            let update = {...options}
            update.mode = 'cors'
            update.headers = makeHeaders()
            return update
        }

        const requestSettings = {
            method: 'get',
            headers: makeHeaders()
        }

        function restoreUser() {
            return JSON.parse(localStorage.getItem('user'))
        }
    </script>

</@main.layout>
