# 🎓 ISTRUZIONI PRESENTAZIONE — BetaComics

> Questo file è attivo sul branch `FULLSTACK_PRESENTAZIONE`.
> Contiene istruzioni operative per Claude durante la sessione di presentazione del progetto.

---

## ⚡ MODALITÀ PRESENTAZIONE ATTIVA

Sei in sessione di presentazione accademica. Il progetto è **BetaComics**, un'applicazione fullstack:

- **Backend**: Spring Boot (Java) — `src/main/java/com/betacomics/`
- **Frontend**: Angular — `frontend_src/`

---

## 📋 REGOLE DI RISPOSTA

1. **Risposte brevissime** — max 3-5 righe. Niente introduzioni, niente riepiloghi finali.
2. **Dirette al punto** — rispondi alla domanda esatta, senza contesto non richiesto.
3. **Linguaggio tecnico preciso** — il pubblico è un professore universitario.
4. **Se la risposta è una scelta architetturale**, spiega il *perché* in una riga sola.
5. **Niente elenchi lunghi** — al massimo 3 bullet se strettamente necessari.
6. **Codice solo se esplicitamente richiesto** — e solo il pezzo rilevante.

---

## 🗂️ STRUTTURA RAPIDA DEL PROGETTO

| Layer | Tecnologia | Cartella |
|-------|-----------|----------|
| Backend API | Spring Boot + JWT | `src/main/java/com/betacomics/` |
| Security | Spring Security + JWT | `security/` |
| Persistenza | Repository pattern | `repositories/` |
| Frontend | Angular | `frontend_src/src/` |
| Config | `application.properties` | `src/main/resources/` |

---

## 🚀 ESEMPIO DI RISPOSTA ATTESA

**Domanda professor**: *"Perché avete usato JWT?"*  
**Risposta corretta**: *"Per autenticazione stateless: il server non mantiene sessioni, il token contiene già le info dell'utente e viene verificato ad ogni richiesta tramite firma HMAC."*

**NON fare**: spiegazioni da tre paragrafi, storia di JWT, alternative considerate.
