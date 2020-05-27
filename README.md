# MovieU

App that will connect to the OMDb API Open Movie Database and show movies and data from OMDb.
So we got our first movies from remote database.

![Image description](https://i.ibb.co/rGyJ8cC/screenshot.jpg)

What we will use:
Clean Architecture,
Modularization,
ListAdapter,
MVVM,
JetPack Navigation,
Dagger2,
REST API,
Retrofit,
Coil,
OkHttp3,
Room,
RxJava.

To do:
- [ ] Work on the movie detail
- [ ] Store the data to our local database with Room


In Progress:
- [ ] Add search function

Done:
- [x] Set up android-ui, cache, common, data, domain and remote modules with dependencies
- [x] Dagger2 implementation
- [x] Set up 2 fragments (with listAdapter and description)
- [x] Connected to the remote, api works
- [x] Refractor (move PhotoServiceFactory and Remote Module from UI module)
- [x] Display an list of data and images (Adapter, List Fragment and ListViewModel)
- [x] Click on item inside of a list goes to movie detail
