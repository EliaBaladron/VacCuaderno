package com.example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.Ayudas.Ayuda_Animales;
import com.example.BD.Animales.BD_Animales;
import com.example.BD.Animales.BD_Animales_Terneros;
import com.example.BD.Animales.BD_Animales_Vacas;
import com.example.BD.Compraventa.BD_Compraventa_Compras;
import com.example.BD.Compraventa.BD_Compraventa_Ventas;
import com.example.BD.Crotales.BD_Crotales_Faltan;
import com.example.BD.Crotales.BD_Crotales_Pedidos;
import com.example.BD.Crotales.BD_Crotales_Recibidos;
import com.example.BD.Crotales.BD_Crotales_SinPoner;
import com.example.BD.Rebaño.BD_Rebaños;
import com.example.BD.Veterinario.BD_Veterinario_Controles;
import com.example.BD.Veterinario.BD_Veterinario_Visitas;
import com.example.ClasesVO.Animales.Animal;
import com.example.ClasesVO.Animales.Ternero;
import com.example.ClasesVO.Animales.Vaca;
import com.example.FeedReader.FeedReaderDbHelper_VacApp;

import com.example.PagerAdapter.PagerAdapter_Animales;
import com.example.PagerAdapter.PagerAdapter_Compras;
import com.example.PagerAdapter.PagerAdapter_Crotales;
import com.example.PagerAdapter.PagerAdapter_Datos;
import com.example.PagerAdapter.PagerAdapter_Rebano;
import com.example.PagerAdapter.PagerAdapter_Ventas;
import com.example.PagerAdapter.PagerAdapter_Veterinario;
import com.example.prueba03.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

/**
 * @author Elia Baladrón Peral
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    public static String DATOS = "Datos";
    public static String EDITAR = "Editar";

    FeedReaderDbHelper_VacApp dbHelper_vacApp;

    public static BD_Animales bdAnimales;
    public static BD_Animales_Terneros bdAnimalesTerneros;
    public static BD_Animales_Vacas bdAnimalesVacas;
    public BD_Crotales_Faltan bdCrotalesFaltan;
    public BD_Crotales_Pedidos bdCrotalesPedidos;
    public BD_Crotales_Recibidos bdCrotalesRecibidos;
    public BD_Crotales_SinPoner bdCrotalesSinPoner;
    public BD_Rebaños bdRebanos;
    public BD_Veterinario_Controles bdVeterinarioControles;
    public BD_Veterinario_Visitas bdVeterinarioVisitas;
    public BD_Compraventa_Compras bdCompraventaCompras;
    public BD_Compraventa_Ventas bdCompraventaVentas;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    ViewPager viewPager;
    TabLayout tabLayout;

    //Animales
    PagerAdapter_Animales adapter;
    TabItem itemAnimales, itemVacas, itemTerneros, itemToros;
    //Crotales
    PagerAdapter_Crotales adapter_crotales;
    TabItem itemFaltan, itemPedidos, itemRecibidos, itemSinPoner;
    //Rebaño
    PagerAdapter_Rebano adapter_rebano;
    TabItem itemRebano;
    //Veterinario
    PagerAdapter_Veterinario adapter_veterinario;
    TabItem itemControles, itemVisitas;
    //Datos
    PagerAdapter_Datos adapter_datos;
    TabItem itemExportar, itemImportar, itemDestete, itemDinero;
    //Compras
    PagerAdapter_Compras adapter_compras;
    TabItem itemCompras;
    //Ventas
    PagerAdapter_Ventas adapter_ventas;
    TabItem itemVentas, itemPlanificadas, itemRealizadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        iniciar();

        //insertarDatosPrueba();

        iniciarAnimales();

    }

    void iniciar(){
        dbHelper_vacApp = new FeedReaderDbHelper_VacApp(getApplicationContext());

        bdAnimales = new BD_Animales(dbHelper_vacApp);
        bdAnimalesTerneros = new BD_Animales_Terneros(dbHelper_vacApp);
        bdAnimalesVacas = new BD_Animales_Vacas(dbHelper_vacApp);
        bdCrotalesFaltan = new BD_Crotales_Faltan(dbHelper_vacApp);
        bdCrotalesPedidos = new BD_Crotales_Pedidos(dbHelper_vacApp);
        bdCrotalesRecibidos = new BD_Crotales_Recibidos(dbHelper_vacApp);
        bdCrotalesSinPoner = new BD_Crotales_SinPoner(dbHelper_vacApp);
        bdRebanos = new BD_Rebaños(dbHelper_vacApp);
        bdVeterinarioControles = new BD_Veterinario_Controles(dbHelper_vacApp);
        bdVeterinarioVisitas = new BD_Veterinario_Visitas(dbHelper_vacApp);
        bdCompraventaCompras = new BD_Compraventa_Compras(dbHelper_vacApp);
        bdCompraventaVentas = new BD_Compraventa_Ventas(dbHelper_vacApp);
    }

    void insertarDatosPrueba(){
        bdAnimales.insertarDatos();
        bdAnimalesTerneros.insertarDatos();
        bdAnimalesVacas.insertarDatos();
        bdCrotalesFaltan.insertarDatos();
        bdCrotalesPedidos.insertarDatos();
        bdCrotalesRecibidos.insertarDatos();
        bdCrotalesSinPoner.insertarDatos();
        bdRebanos.insertarDatos();
        bdVeterinarioControles.insertarDatos();
        bdVeterinarioVisitas.insertarDatos();
        bdCompraventaCompras.insertarDatos();
        bdCompraventaVentas.insertarDatos();
    }

    void iniciarAnimales(){
        setContentView(R.layout.tab_animales);

        Toolbar toolbar = findViewById(R.id.animales_toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.animales_viewPager);
        tabLayout = findViewById(R.id.animales_tabLayout);

        itemAnimales = findViewById(R.id.itemAnimales);
        itemVacas = findViewById(R.id.itemVacas);
        itemTerneros = findViewById(R.id.itemTerneros);
        itemToros = findViewById(R.id.itemToros);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        adapter = new PagerAdapter_Animales(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, tabLayout.getTabCount(), this);
        viewPager.setAdapter(adapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //Ayuda

        FloatingActionButton fab_help;
        fab_help = findViewById(R.id.fab_help);
        fab_help.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Ayuda_Animales.class);
            startActivity(intent);
        });
    }
    void iniciarCompras(){
        setContentView(R.layout.tab_compraventa_compras);

        Toolbar toolbar = findViewById(R.id.compras_toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.compras_viewPager);
        tabLayout = findViewById(R.id.compras_tabLayout);

        itemCompras = findViewById(R.id.itemCompras);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        adapter_compras = new PagerAdapter_Compras(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, tabLayout.getTabCount(), this);
        viewPager.setAdapter(adapter_compras);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    void iniciarVentas(){
        setContentView(R.layout.tab_compraventa_ventas);

        Toolbar toolbar = findViewById(R.id.ventas_toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.ventas_viewPager);
        tabLayout = findViewById(R.id.ventas_tabLayout);

        itemVentas = findViewById(R.id.itemVentas);
        itemPlanificadas = findViewById(R.id.itemPlanificadas);
        itemRealizadas = findViewById(R.id.itemRealizadas);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        adapter_ventas = new PagerAdapter_Ventas(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, tabLayout.getTabCount(), this);
        viewPager.setAdapter(adapter_ventas);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    void iniciarCrotales(){
        setContentView(R.layout.tab_crotales);

        Toolbar toolbar = findViewById(R.id.crotales_toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.crotales_viewPager);
        tabLayout = findViewById(R.id.crotales_tabLayout);

        itemFaltan = findViewById(R.id.itemFaltan);
        itemPedidos = findViewById(R.id.itemPedidos);
        itemRecibidos = findViewById(R.id.itemRecibidos);
        itemSinPoner = findViewById(R.id.itemSinPoner);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        adapter_crotales = new PagerAdapter_Crotales(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, tabLayout.getTabCount(), this);
        viewPager.setAdapter(adapter_crotales);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    void iniciarVeterinario(){
        setContentView(R.layout.tab_veterinario);

        Toolbar toolbar = findViewById(R.id.veterinario_toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.veterinario_viewPager);
        tabLayout = findViewById(R.id.veterinario_tabLayout);

        itemControles = findViewById(R.id.itemControles);
        itemVisitas = findViewById(R.id.itemVisitas);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        adapter_veterinario = new PagerAdapter_Veterinario(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, tabLayout.getTabCount(), this);
        viewPager.setAdapter(adapter_veterinario);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    void iniciarDatos(){
        setContentView(R.layout.tab_datos);

        Toolbar toolbar = findViewById(R.id.datos_toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.datos_viewPager);
        tabLayout = findViewById(R.id.datos_tabLayout);

        itemExportar = findViewById(R.id.itemExportar);
        itemImportar = findViewById(R.id.itemImportar);
        itemDestete = findViewById(R.id.itemDestete);
        itemDinero = findViewById(R.id.itemDinero);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        adapter_datos = new PagerAdapter_Datos(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, tabLayout.getTabCount(), this);
        viewPager.setAdapter(adapter_datos);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    void iniciarRebano(){
        setContentView(R.layout.tab_rebano);

        Toolbar toolbar = findViewById(R.id.rebano_toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.rebano_viewPager);
        tabLayout = findViewById(R.id.rebano_tabLayout);

        itemRebano = findViewById(R.id.itemRebano);

        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        adapter_rebano = new PagerAdapter_Rebano(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, tabLayout.getTabCount(), this);
        viewPager.setAdapter(adapter_rebano);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        /*if(item.getItemId() == R.id.menu_animales){
            Toast.makeText(this, "Boton animales", Toast.LENGTH_SHORT).show();
        }*/

        switch (item.getItemId()){
            case R.id.menu_animales:
                Toast.makeText(this, "Área animales", Toast.LENGTH_SHORT).show();
                iniciarAnimales();
                break;
            case R.id.menu_compras:
                Toast.makeText(this, "Área compras", Toast.LENGTH_SHORT).show();
                iniciarCompras();
                break;
            case R.id.menu_rebano:
                Toast.makeText(this, "Área rebaño", Toast.LENGTH_SHORT).show();
                iniciarRebano();
                break;
            case R.id.menu_ventas:
                Toast.makeText(this, "Área ventas", Toast.LENGTH_SHORT).show();
                iniciarVentas();
                break;
            case R.id.menu_crotales:
                Toast.makeText(this, "Área crotales", Toast.LENGTH_SHORT).show();
                iniciarCrotales();
                break;
            case R.id.menu_veterinario:
                Toast.makeText(this, "Área veterinario", Toast.LENGTH_SHORT).show();
                iniciarVeterinario();
                break;
            case R.id.menu_datos:
                Toast.makeText(this, "Área datos", Toast.LENGTH_SHORT).show();
                iniciarDatos();
                break;
        }

        return false;
    }


    public static ArrayList<Ternero> obtenerTerneros(){
        ArrayList<Ternero> terneros = bdAnimalesTerneros.getDatosObjetos();

        for(Ternero ternero: terneros){
            ArrayList<Animal> t = bdAnimales.getDatosObjeto(ternero.getCrotal());
            ternero.setAnimal((Animal) t.get(0));
        }
        return terneros;
    }

    public static void anadir(Animal animal){
        bdAnimales.insertarDatos(animal);
    }
    public static void anadir(Ternero ternero){
        bdAnimalesTerneros.insertarDatos(ternero);
    }
    public static void anadir(Vaca vaca){
        bdAnimalesVacas.insertarDatos(vaca);
    }

    public static void actualizar(Animal animal){
        bdAnimales.actualizarBD(animal);
    }
    public static void actualizar(Ternero ternero){
        bdAnimalesTerneros.actualizarBD(ternero);
    }
    public static void actualizar(Vaca vaca){
        bdAnimalesVacas.actualizarBD(vaca);
    }

    public static void eliminar(Animal animal){
        bdAnimales.borrarDatos(animal);
    }
    public static void eliminar(Ternero ternero){
        bdAnimalesTerneros.borrarDatos(ternero);
    }
    public static void eliminar(Vaca vaca){
        bdAnimalesVacas.borrarDatos(vaca);
    }


    /**
     * Si existe un ternero con el crotal pasado se elimina de la BD
     * @param crotal    Crotal a comprobar y borrar si existe
     */
    public static void eliminarTernero(String crotal){
        ArrayList<Ternero> terneros = bdAnimalesTerneros.getDatosObjeto(crotal);
        if(terneros.size() > 0)
            bdAnimalesTerneros.borrarDatos(terneros.get(0));
    }
    /**
     * Si existe una vaca con el crotal pasado se elimina de la BD
     * @param crotal    Crotal a comprobar y borrar si existe
     */
    public static void eliminarVaca(String crotal){
        ArrayList<Vaca> vacas = bdAnimalesVacas.getDatosObjeto(crotal);

        if(vacas.size() > 0)
            bdAnimalesVacas.borrarDatos(vacas.get(0));
    }


}