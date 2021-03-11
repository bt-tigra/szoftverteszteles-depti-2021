package hu.tigra.pti.geb

import hu.tigra.pti.geb.domain.User

class Constant {

    static final USERS = [
            HELYES_FELHASZNALO: new User(emailAddress: 'gebpti2021@tigra.hu', password: 'teszt123'),
            HELYTELEN_FELHASZNALO: new User(emailAddress: 'gebpti2021@tigra.hu', password: 'rosszjelszo')
    ]
}
