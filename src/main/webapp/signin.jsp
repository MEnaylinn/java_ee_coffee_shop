<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%> <%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <c:import url="common/header.html" />
    <meta charset="ISO-8859-1" />
    <title>Sing in Form</title>
  </head>
  <body>
    <section class="vh-100 bg-dark">
      <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
          <div class="col col-xl-10">
            <div class="card" style="border-radius: 1rem">
              <div class="row g-0">
                <div class="col-md-6 col-lg-5 d-none d-md-block">
                  <img
                    src="assets/coffeethemes.jpeg"
                    alt="login form"
                    class="img-fluid"
                    style="
                      border-radius: 1rem 0 0 1rem;
                      width: 200%;
                      heigh: 200%;
                    "
                  />
                </div>
                <div class="col-md-6 col-lg-7 d-flex align-items-center">
                  <div class="card-body p-4 p-lg-5 text-black">
                    <form action="login" method="post">
                      <input type="hidden" name="mode" value="LOGIN" />

                      <div class="d-flex align-items-center mb-3 pb-1">
                        <i
                          class="fas fa-cubes fa-2x me-3"
                          style="color: #ff6219"
                        ></i>
                        <span class="h1 fw-bold mb-0">Classic Cafe</span>
                      </div>

                      <div class="form-outline mb-4">
                        <input
                          type="text"
                          id="phone"
                          name="phone"
                          class="form-control form-control-lg"
                        />
                        <label class="form-label" for="phone">Phone</label>
                      </div>

                      <div class="form-outline mb-4">
                        <input
                          type="password"
                          id="password"
                          name="password"
                          class="form-control form-control-lg"
                        />
                        <label class="form-label" for="password"
                          >Password</label
                        >
                      </div>

                      <div class="pt-1 mb-4">
                        <button type="submit" class="btn btn-primary">
                          Login
                        </button>
                      </div>

                      <p class="mb-5 pb-lg-2" style="color: #393f81">
                        Don't have an account?
                        <a href="signup.jsp" style="color: #393f81"
                          >Register here</a
                        >
                      </p>
                      <a href="#!" class="small text-muted">Terms of use.</a>
                      <a href="#!" class="small text-muted">Privacy policy</a>
                    </form>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <c:import url="common/footer.html" />
  </body>
</html>
