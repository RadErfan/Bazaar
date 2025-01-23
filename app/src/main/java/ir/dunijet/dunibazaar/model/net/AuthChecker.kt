package ir.dunijet.dunibazaar.model.net

import ir.dunijet.dunibazaar.model.data.LoginResponse
import ir.dunijet.dunibazaar.model.repository.TokenInMemory
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Call
import retrofit2.Retrofit

class AuthChecker : Authenticator, KoinComponent {

    // in tabe authenticate zamani call mishe ke az samte server error 401 dade beshe
    // error 401 zamani pish miyad ke masala karbar passwordesh ro avaz karde ya token ma expire shode
    private val apiService: ApiService by inject()

    override fun authenticate(route: Route?, response: Response): Request? {

        if (TokenInMemory.token != null && !response.request.url.pathSegments.last().equals("refreshToken", false)) {

            val result = refreshToken()
            if (result) {
                return response.request
            }

        }

        return null
    }

        private fun refreshToken(): Boolean {
            val request: retrofit2.Response<LoginResponse> = apiService.refreshToken().execute() // to in khat be sorat acycornose request mizane be server va etelat ro daryaft mikone
            if (request.body() != null) {
                if (request.body()!!.success) {
                    return true
                }
            }

            return false
        }


    }