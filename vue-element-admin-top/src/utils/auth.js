import Cookies from 'js-cookie'

const TokenKey = 'access_token'

export function getToken() {
  let token = Cookies.get(TokenKey)
  if (token) {
    return token
  }
  return ''
}

export function setToken(token) {
  return Cookies.set(TokenKey, token)
}

export function removeToken() {
  return Cookies.remove(TokenKey)
}
