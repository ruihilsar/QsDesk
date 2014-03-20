//************				Qian Han						************
//************		Generator for Graphics document			************
//************				May,11 2011						************
#include <stdlib.h>
#include <stdio.h>
#include <GL/glut.h>
#include <windows.h>
#include <math.h>

#include <iostream>
#include <fstream>
#include <string>

using namespace std;

const GLfloat EPS=0.001;

const int MAX_LIGHT = 20;
const int MAX_PIGMENT = 25;
const int MAX_SURFACE = 25;
const int MAX_OBJECT = 100;
const int MAX_POLY_SUR = 20;
const int MAX_TRANSFORMATION = 20;
const int MAX = 4;
const int MAX_VERTICE = 100000;
const int MAX_TRIANGLE = 100000;

bool antiA=false;  // true if anti-A
bool inter=false;  // true if intersect
int obj=0;  // 0 -> sphere, 1 -> polyhedron, 2 -> triangle meshes
int count=-1;  // store the index of intersected object
int count1=-1;  // use for polyhedron and trianglemesh
bool outside=true;  //outside
GLfloat ior; // store the ior

GLfloat* pixel;

const int LINE_LENGTH = 100;
char inFile[LINE_LENGTH];
char outFile[LINE_LENGTH];
int width;
int height;
int data[300][300][3];
GLfloat pigmData[300][300][3];//test
int object[300][300];
GLfloat ln[300][300];//test
GLfloat ln1;//test
GLfloat kds[300][300];//test
GLfloat camera[3];
GLfloat at[3];
GLfloat up[3];
GLfloat fovy;

int numOfVertice;
int numOfTriangle;
GLfloat vertice[MAX_VERTICE][3];
int triangle[MAX_TRIANGLE][3];

void normalize(float *p) //normalize a vector
{
    float d =0.0;
    int i;
    for(i=0; i<3; i++) d+=p[i]*p[i];
    d=sqrt(d);
    if(d>0.0) for(i=0; i<3; i++) p[i]/=d;
}

int numOfLight;
typedef struct light
{
	GLfloat posOfLight[3];
	GLfloat colOfLight[3];
	GLfloat intOfLight[3];
}light;
light lights[MAX_LIGHT];

int numP;
typedef struct pigment
{
	int type;
	GLfloat color1[3];
	GLfloat color2[3];
	GLfloat length;
}pigment;
pigment pigments[MAX_PIGMENT];

int numF;
typedef struct surface
{
	GLfloat ka;
	GLfloat kd;
	GLfloat ks;
	GLfloat shininess;
	GLfloat kr;
	GLfloat kt;
	GLfloat ior;
}surface;
surface surfaces[MAX_SURFACE];

int numT;
typedef struct transformation
{
	int type; // 0 -> scale, 1 -> translate
	GLfloat x;
	GLfloat y;
	GLfloat z;
}transformation;
transformation transformations[MAX_TRANSFORMATION];

int numO;

int numOfS;
typedef struct sphere
{
	int pigment_num;
	int surface_num;
	int numOfT;
	int transformation_num[MAX_TRANSFORMATION];
	GLfloat center_point[3];
	GLfloat radius;
}sphere;
sphere spheres[MAX_OBJECT];

int numOfP;
typedef struct polyhedron
{
	int pigment_num;
	int surface_num;
	int  numFace;
	GLfloat face[MAX_POLY_SUR][4];	
}polyhedron;
polyhedron polyhedrons[MAX_OBJECT];

int numOfTM;
typedef struct trianglemesh
{
	int pigment_num;
	int surface_num;
	int numOfT;
	int transformation_num[MAX_TRANSFORMATION];
	char ply[LINE_LENGTH];
	int numVertice;
	int numTriangle;
}trianglemesh;
trianglemesh trianglemeshes[MAX_OBJECT];

typedef struct Color
{
	GLfloat r;
	GLfloat g;
	GLfloat b;
}Color;
Color bgc = {0.5,0.5,0.5};

typedef struct Ray
{
	GLfloat xs;  // x value of the start point
	GLfloat ys;
	GLfloat zs;
	GLfloat x;
	GLfloat y;
	GLfloat z;
}Ray;

typedef struct Point
{
	GLfloat x;
	GLfloat y;
	GLfloat z;
}Point;

typedef struct Vector
{
	GLfloat x;
	GLfloat y;
	GLfloat z;
}Vector;
Vector normal;

Vector normalizeV(Vector p) //normalize a vector
{
	Vector returnValue;
	float d=p.x*p.x+p.y*p.y+p.z*p.z;
    d=sqrt(d);
	if(d>0) {
		returnValue.x=p.x/d;
		returnValue.y=p.y/d;
		returnValue.z=p.z/d;
	}
	return returnValue;
}

Vector minus(Point a,Point b) {
	Vector returnValue;
	returnValue.x=a.x-b.x;
	returnValue.y=a.y-b.y;
	returnValue.z=a.z-b.z;
	return returnValue;
}

Vector minusV(Vector a,Vector b) {
	Vector returnValue;
	returnValue.x=a.x-b.x;
	returnValue.y=a.y-b.y;
	returnValue.z=a.z-b.z;
	return returnValue;
}

Vector product(float a,Vector b) {
	Vector returnValue;
	returnValue.x=a*b.x;
	returnValue.y=a*b.y;
	returnValue.z=a*b.z;
	return returnValue;

}

float dproduct(Vector a,Vector b) {
	float returnValue=a.x*b.x+a.y*b.y+a.z*b.z;
	return returnValue;
}

Vector cproduct(Vector a,Vector b)
{
	struct Vector returnValue;
	returnValue.x=a.y*b.z-a.z*b.y;
	returnValue.y=a.z*b.x-a.x*b.z;
	returnValue.z=a.x*b.y-b.x*a.y;
    return returnValue;
}

void reshape(int x, int y){
  glViewport(0,0,x,y);
  glMatrixMode(GL_PROJECTION);
  glLoadIdentity();
  gluPerspective(60,x/(y*1.0),0.1,55.0);  
  glMatrixMode(GL_MODELVIEW);
  glLoadIdentity();  
}

void readPly(char* in)
{
	ifstream fin(in);
	char str[LINE_LENGTH];

	fin >> str;
	numOfVertice=atoi(str);
	fin >> str;
	numOfTriangle=atoi(str);
	printf("%d %d\n",numOfVertice,numOfTriangle);

	for(int i=0;i<numOfVertice;i++) {
		fin >> str;
		printf(str);
		if(strcmp(str,"v")==0)
		{
			fin >> str;
			vertice[i][0]=atof(str);
			fin >> str;
			vertice[i][1]=atof(str);
			fin >> str;
			vertice[i][2]=atof(str);
			printf(" %f %f %f\n",vertice[i][0],vertice[i][1],vertice[i][2]);
		}
	}
	for(int i=0;i<numOfTriangle;i++) {
		fin >> str;
		printf(str);
		if(strcmp(str,"f")==0)
		{
			fin >> str;
			triangle[i][0]=atoi(str);
			fin >> str;
			triangle[i][1]=atoi(str);
			fin >> str;
			triangle[i][2]=atoi(str);
			printf(" %d %d %d\n",triangle[i][0],triangle[i][1],triangle[i][2]);
		}
	}
}

// read file
void readFile(char* in)
{
	
	ifstream fin(in);

	fin >> outFile;	
	printf("%s\n",outFile);
	
	char str[LINE_LENGTH];

	fin >> str;
	if(str=="antialiasing")
	{
		antiA=true;
		fin >> str;
	}
	width=atoi(str);
	fin >> str;
	height=atoi(str);
	printf("%d %d\n",width,height);
	
	fin >> str;
	camera[0]=atof(str);
	fin >> str;
	camera[1]=atof(str);
	fin >> str;
	camera[2]=atof(str);
	
	printf("%f %f %f\n",camera[0],camera[1],camera[2]);

	fin >> str;
	at[0]=atof(str);
	fin >> str;
	at[1]=atof(str);
	fin >> str;
	at[2]=atof(str);
	printf("%f %f %f\n",at[0],at[1],at[2]);

	fin >> str;
	up[0]=atof(str);
	fin >> str;
	up[1]=atof(str);
	fin >> str;
	up[2]=atof(str);
	printf("%f %f %f\n",up[0],up[1],up[2]);

	fin >> str;
	fovy=atof(str);
	
	printf("%f\n",fovy);

	fin >> str;
	numOfLight=atoi(str);
	printf("%d\n",numOfLight);
	
	

	if(numOfLight>MAX_LIGHT)
	{
		exit(0);
	}
	
	for(int i=0;i<numOfLight;i++)
	{
		light l;
		fin >> str;
		l.posOfLight[0]=atof(str);
		fin >> str;
		l.posOfLight[1]=atof(str);
		fin >> str;
		l.posOfLight[2]=atof(str);
		
		fin >> str;
		l.colOfLight[0]=atof(str);
		fin >> str;
		l.colOfLight[1]=atof(str);
		fin >> str;
		l.colOfLight[2]=atof(str);
		
		fin >> str;
		l.intOfLight[0]=atof(str);
		fin >> str;
		l.intOfLight[1]=atof(str);
		fin >> str;
		l.intOfLight[2]=atof(str);
		
		lights[i]=l;

		printf("%f %f %f %f %f %f %f %f %f\n",l.posOfLight[0],l.posOfLight[1],l.posOfLight[2],l.colOfLight[0],l.colOfLight[1],l.colOfLight[2],l.intOfLight[0],l.intOfLight[1],l.intOfLight[2]);
	}


	fin >> str;
	numP=atoi(str);
	
	if(numP>MAX_PIGMENT)
	{
		exit(0);
	}
	printf("%d\n",numP);
	
	for(int i=0;i<numP;i++)
	{
		fin >> str;
		printf(str);
		pigment p;
		if(strcmp(str,"solid")==0)
		{
			p.type=0;
			fin >> str;
			p.color1[0]=atof(str);
			fin >> str;
			p.color1[1]=atof(str);
			fin >> str;
			p.color1[2]=atof(str);
			pigments[i]=p;
			printf(" %f %f %f\n",p.color1[0],p.color1[1],p.color1[2]);
		}
		if(strcmp(str,"checker")==0)
		{
			p.type=1;
			fin >> str;
			p.color1[0]=atof(str);
			fin >> str;
			p.color1[1]=atof(str);
			fin >> str;
			p.color1[2]=atof(str);
			pigments[i]=p;
			fin >> str;
			p.color2[0]=atof(str);
			fin >> str;
			p.color2[1]=atof(str);
			fin >> str;
			p.color2[2]=atof(str);
			fin >> str;
			p.length=atof(str);
			pigments[i]=p;
			printf(" %f %f %f %f %f %f\n",p.color1[0],p.color1[1],p.color1[2],p.color2[0],p.color2[1],p.color2[2]);
		}
	}
	
	fin >> str;
	numF=atoi(str);
	
	if(numF>MAX_SURFACE)
	{
		exit(0);
	}
	
	printf("%d\n",numF);

	for(int i=0;i<numF;i++)
	{
		surface s;
		fin >> str;
		s.ka=atof(str);
		fin >> str;
		s.kd=atof(str);
		fin >> str;
		s.ks=atof(str);
		fin >> str;
		s.shininess=atof(str);
		fin >> str;
		s.kr=atof(str);
		fin >> str;
		s.kt=atof(str);
		fin >> str;
		s.ior=atof(str);
		surfaces[i]=s;
		printf("%f %f %f %f %f %f %f\n",s.ka,s.kd,s.ks,s.shininess,s.kr,s.kt,s.ior);
	}

	fin >> str;
	numT=atoi(str);

	if(numT>MAX_TRANSFORMATION) {
		exit(0);
	}
	printf("%d\n",numT);

	if(numT>0) {
		for (int i=0;i<numT;i++) {
			transformation t;
			fin >> str;
			if(strcmp(str,"scale")==0) t.type=0;
			if(strcmp(str,"translate")==0) t.type=1;
			fin >> str;
			t.x=atof(str);
			fin >> str;
			t.y=atof(str);
			fin >> str;
			t.z=atof(str);
			transformations[i]=t;
			printf("%d %f %f %f\n",t.type,t.x,t.y,t.z);
		}
	}
	
	fin >> str;
	numO=atoi(str);
	
	if(numO>MAX_OBJECT)
	{
		exit(0);
	}
	
	printf("%d\n",numO);

	numOfS=0;
	numOfP=0;
	numOfTM=0;
	for(int i=0;i<numO;i++)
	{
		fin >> str;
		int p=atoi(str);
		fin >> str;
		int s=atoi(str);
		fin >> str;
		int t=atoi(str);
		printf("%d %d %d ",p,s,t);
		int trans[MAX_TRANSFORMATION];
		if(t>0) {
			for(int j=0;j<t;j++) {
				fin >> str;
				trans[j]=atoi(str);
				printf("%d ",trans[j]);                                              
			}
		}


		fin >> str;
		printf("\%s ",str);
		if(strcmp(str,"sphere")==0)
		{
			sphere sp;
			sp.pigment_num=p;
			sp.surface_num=s;
			sp.numOfT=t;
			if(sp.numOfT>0) {
				for(int j=0;j<sp.numOfT;j++)
					sp.transformation_num[j]=trans[j];
			}
			fin >> str;
			sp.center_point[0]=atof(str);
			fin >> str;
			sp.center_point[1]=atof(str);
			fin >> str;
			sp.center_point[2]=atof(str);
			fin >> str;
			sp.radius=atof(str);
			for(int j=0;j<sp.numOfT;j++) {
				if(transformations[sp.transformation_num[j]].type==0) // scale
					sp.radius*=transformations[sp.transformation_num[j]].x;
				else { // translate
					sp.center_point[0]+=transformations[sp.transformation_num[j]].x;
					sp.center_point[1]+=transformations[sp.transformation_num[j]].y;
					sp.center_point[2]+=transformations[sp.transformation_num[j]].z;
				}
			}
			printf("%f %f %f %f\n",sp.center_point[0],sp.center_point[1],sp.center_point[2],sp.radius);
			spheres[numOfS]=sp;
			numOfS++;
		}
		if(strcmp(str,"polyhedron")==0)
		{
			polyhedron po;
			po.pigment_num=p;
			po.surface_num=s;
			fin >> str;
			po.numFace=atoi(str);
			printf("%d\n",po.numFace);
			for(int j=0;j<po.numFace;j++)
			{
				printf("       ");
				fin >> str;
				po.face[j][0]=atof(str);
				fin >> str;
				po.face[j][1]=atof(str);
				fin >> str;
				po.face[j][2]=atof(str);
				fin >> str;
				po.face[j][3]=atof(str);
				printf("%f %f %f %f",po.face[j][0],po.face[j][1],po.face[j][2],po.face[j][3]);
			}
			polyhedrons[numOfP]=po;
			numOfP++;
		}
		if(strcmp(str,"trianglemesh")==0)
		{
			trianglemesh tm;
			tm.pigment_num=p;
			tm.surface_num=s;
			tm.numOfT=t;
			if(tm.numOfT>0) {
				for(int j=0;j<tm.numOfT;j++)
					tm.transformation_num[j]=trans[j];
			}
			fin >> tm.ply;
			printf("%s\n",tm.ply);
			readPly(tm.ply);
			tm.numVertice=numOfVertice;
			tm.numTriangle=numOfTriangle;
			for(int j=0;j<numOfVertice;j++) {
				for(int k=0;k<tm.numOfT;k++) {
					if(transformations[tm.transformation_num[k]].type==0) {  // scale
						vertice[j][0]*=transformations[tm.transformation_num[k]].x;
						vertice[j][1]*=transformations[tm.transformation_num[k]].y;
						vertice[j][2]*=transformations[tm.transformation_num[k]].z;
					}
					else { // translate
						vertice[j][0]+=transformations[tm.transformation_num[k]].x;
						vertice[j][1]+=transformations[tm.transformation_num[k]].y;
						vertice[j][2]+=transformations[tm.transformation_num[k]].z;
					}
				}
				//printf(" %f %f %f\n",vertice[j][0],vertice[j][1],vertice[j][2]);
			}
			trianglemeshes[numOfTM]=tm;
			numOfTM++;
		}
	}
}

bool sameSide(Point p,Point a1,Point a2,Point a3) {
	Vector cp1=cproduct(minus(a2,a1),minus(p,a1));
	Vector cp2=cproduct(minus(a2,a1),minus(a3,a1));
	if (dproduct(cp1,cp2)>=0)
		return true;
	else
		return false;
}

bool pointInTriangle(Point p,Point a,Point b,Point c) {
	if(sameSide(p,a,b,c)&&sameSide(p,b,a,c)&&sameSide(p,c,a,b))
		return true;
	else 
		return false;
}

Point intersect(Ray R) {
	GLfloat t=100000;
	inter=false;
	Point returnValue;
	GLfloat d[3]={R.x,R.y,R.z};  //direction of the ray
	normalize(d); // normalize
	for(int i=0;i<numOfS;i++) { // check if it intersected with spheres
		GLfloat u[3]={spheres[i].center_point[0]-R.xs,spheres[i].center_point[1]-R.ys,spheres[i].center_point[2]-R.zs};  // u vector is center of the sphere minus the strat point of the ray
		GLfloat temp=4*(d[0]*u[0]+d[1]*u[1]+d[2]*u[2])*(d[0]*u[0]+d[1]*u[1]+d[2]*u[2])-4*((u[0]*u[0]+u[1]*u[1]+u[2]*u[2])-spheres[i].radius*spheres[i].radius);  // temp=b^2-4ac; a=d.d=1; b=-2(d.u); c=u.u-r.r;
		if(temp>=EPS){
			GLfloat temp1=sqrt(temp);
			GLfloat t1=d[0]*u[0]+d[1]*u[1]+d[2]*u[2]-temp1/2;
			GLfloat t2=d[0]*u[0]+d[1]*u[1]+d[2]*u[2]+temp1/2;
			if((t1>=EPS)&&((t-t1)>EPS)){
				t=t1;
				inter=true;
				obj=0;
				count=i;
				outside=true;
				returnValue.x=R.xs+t*d[0]; // x value of the intersection
				returnValue.y=R.ys+t*d[1];
				returnValue.z=R.zs+t*d[2];
				/*GLfloat normal1[3]={returnValue.x-spheres[i].center_point[0],returnValue.y-spheres[i].center_point[1],returnValue.z-spheres[i].center_point[2]}; // compute the normal
				normalize(normal1);
				normal.x=normal1[0];
				normal.y=normal1[1];
				normal.z=normal1[2];*/
			}
			if((t2>=EPS)&&((t-t2)>EPS)){
				t=t2;
				inter=true;
				obj=0;
				count=i;
				outside=false;
				returnValue.x=R.xs+t*d[0];
				returnValue.y=R.ys+t*d[1];
				returnValue.z=R.zs+t*d[2];
				/*GLfloat normal1[3]={spheres[i].center_point[0]-returnValue.x,spheres[i].center_point[1]-returnValue.y,spheres[i].center_point[2]-returnValue.z}; // compute the normal
				normalize(normal1);
				normal.x=normal1[0];
				normal.y=normal1[1];
				normal.z=normal1[2];*/
			}
		}
	}
	for(int i=0;i<numOfP;i++) { // check if it intersected with polyhedrons
		int in=-1;
		int out=-1;
		GLfloat tin=-1;
		GLfloat tout=100000;
		for(int j=0;j<polyhedrons[i].numFace;j++) {
			GLfloat denom=polyhedrons[i].face[j][0]*d[0]+polyhedrons[i].face[j][1]*d[1]+polyhedrons[i].face[j][2]*d[2]; //compute the denominator
			GLfloat numer=-polyhedrons[i].face[j][0]*R.xs-polyhedrons[i].face[j][1]*R.ys-polyhedrons[i].face[j][2]*R.zs-polyhedrons[i].face[j][3]; // compute the numerator
			if(numer<0){ // start point outside the face
				if((denom<0)&&((numer/denom-tin)>=EPS)) {
					tin=numer/denom;
					in=j;
				}
			}
			if(numer>=0){ // start point inside the face
				if((denom>0)&&((tout-numer/denom)>=EPS)) {
					tout=numer/denom;
					out=j;
				}
			}
		}
		if(tin-tout<=EPS){ // has intersection
			if((tin>=EPS)&&((t-tin)>EPS)){
				t=tin;
				inter=true;
				obj=1;
				count=i;
				outside=true;
				count1=in;
				returnValue.x=R.xs+t*d[0];
				returnValue.y=R.ys+t*d[1];
				returnValue.z=R.xs+t*d[2];
				/*GLfloat normal1[3]={polyhedrons[i].face[in][0],polyhedrons[i].face[in][1],polyhedrons[i].face[in][2]}; // compute the normal
				normalize(normal1);
				normal.x=normal1[0];
				normal.y=normal1[1];
				normal.z=normal1[2];*/
			}
			if((tout>=EPS)&&((t-tout)>EPS)){
				t=tout;
				inter=true;
				obj=1;
				count=i;
				outside=false;
				count1=out;
				returnValue.x=R.xs+t*d[0];
				returnValue.y=R.ys+t*d[1];
				returnValue.z=R.xs+t*d[2];
				/*GLfloat normal1[3]={-polyhedrons[i].face[out][0],-polyhedrons[i].face[out][1],-polyhedrons[i].face[out][2]}; // compute the normal
				normalize(normal1);
				normal.x=normal1[0];
				normal.y=normal1[1];
				normal.z=normal1[2];*/
			}
		}
	}
	for(int i=0;i<numOfTM;i++) { //triangle mesh
		int triangleNum=-1;
		for(int j=0;j<trianglemeshes[i].numTriangle;j++) {
			Point a1,a2,a3;
			a1.x=vertice[triangle[j][0]][0];
			a1.y=vertice[triangle[j][0]][1];
			a1.z=vertice[triangle[j][0]][2];
			a2.x=vertice[triangle[j][1]][0];
			a2.y=vertice[triangle[j][1]][1];
			a2.z=vertice[triangle[j][1]][2];
			a3.x=vertice[triangle[j][2]][0];
			a3.y=vertice[triangle[j][2]][1];
			a3.z=vertice[triangle[j][2]][2];
			Vector n=cproduct(minus(a2,a1),minus(a3,a1));
			Vector d1;
			d1.x=d[0];
			d1.y=d[1];
			d1.z=d[2];
			Point o,p;
			o.x=R.xs;
			o.y=R.ys;
			o.z=R.zs;
			if(dproduct(d1,n)!=0) {
				GLfloat tt=-dproduct(minus(o,a1),n)/dproduct(d1,n);
				if((tt>=EPS)&&((t-tt)>EPS)) {
					p.x=R.xs+tt*d[0];
					p.y=R.ys+tt*d[1];
					p.z=R.xs+tt*d[2];
					if(pointInTriangle(p,a1,a2,a3)) {
						t=tt;
						inter=true;
						obj=2;
						count=i;
						count1=j;
						returnValue=p;
						/*normal=normalizeV(n);
						printf("%f %f %f\n",normal.x,normal.y,normal.z);*/
					}
				}
			}
		}
	}
	return returnValue;
}

bool visible(Point P,light l) {
	bool returnValue=true;
	GLfloat o[3]={l.posOfLight[0],l.posOfLight[1],l.posOfLight[2]};
	GLfloat d[3]={P.x-o[0],P.y-o[1],P.z-o[2]};
	for(int i=0;i<numOfS;i++) { // check if it intersected with spheres
		GLfloat u[3]={spheres[i].center_point[0]-o[0],spheres[i].center_point[1]-o[1],spheres[i].center_point[2]-o[2]};
		GLfloat temp=4*(d[0]*u[0]+d[1]*u[1]+d[2]*u[2])*(d[0]*u[0]+d[1]*u[1]+d[2]*u[2])-4*(d[0]*d[0]+d[1]*d[1]+d[2]*d[2])*((u[0]*u[0]+u[1]*u[1]+u[2]*u[2])-spheres[i].radius*spheres[i].radius);
		if(temp>=0){
			GLfloat temp1=sqrt(temp);
			GLfloat t1=(d[0]*u[0]+d[1]*u[1]+d[2]*u[2]-temp1/2)/(d[0]*d[0]+d[1]*d[1]+d[2]*d[2]);
			GLfloat t2=(d[0]*u[0]+d[1]*u[1]+d[2]*u[2]+temp1/2)/(d[0]*d[0]+d[1]*d[1]+d[2]*d[2]);
			if((t1>=EPS)&&(t1<(1-EPS))) returnValue=false;
			if((t2>=EPS)&&(t2<(1-EPS))) returnValue=false;
		}
	}
	for(int i=0;i<numOfP;i++) { // check if it intersected with polyhedrons
		GLfloat tin=-1;
		GLfloat tout=100000;
		for(int j=0;j<polyhedrons[i].numFace;j++) {
			GLfloat denom=polyhedrons[i].face[j][0]*d[0]+polyhedrons[i].face[j][1]*d[1]+polyhedrons[i].face[j][2]*d[2]; //compute the denominator
			GLfloat numer=-polyhedrons[i].face[j][0]*o[0]-polyhedrons[i].face[j][1]*o[1]-polyhedrons[i].face[j][2]*o[2]-polyhedrons[i].face[j][3]; // compute the numerator
			if(numer<0){ // start point outside the face
				if((denom<0)&&((numer/denom-tin)>=EPS)) tin=numer/denom;
			}
			if(numer>=0){ // start point inside the face
				if((denom>0)&&((tout-numer/denom)>=EPS)) tout=numer/denom;
			}
		}
		if(tin-tout<=EPS){ // has intersection
			if((tin>=EPS)&&(1-tin>EPS)) returnValue=false;
			if((tout>=EPS)&&(1-tout>EPS)) returnValue=false;
		}
	}
	for(int i=0;i<numOfTM;i++) { //triangle mesh
		for(int j=0;j<trianglemeshes[i].numTriangle;j++) {
			Point a1,a2,a3;
			a1.x=vertice[triangle[j][0]][0];
			a1.y=vertice[triangle[j][0]][1];
			a1.z=vertice[triangle[j][0]][2];
			a2.x=vertice[triangle[j][1]][0];
			a2.y=vertice[triangle[j][1]][1];
			a2.z=vertice[triangle[j][1]][2];
			a3.x=vertice[triangle[j][2]][0];
			a3.y=vertice[triangle[j][2]][1];
			a3.z=vertice[triangle[j][2]][2];
			Vector n=cproduct(minus(a2,a1),minus(a3,a1));
			Vector d1;
			d1.x=d[0];
			d1.y=d[1];
			d1.z=d[2];
			Point o1,p;
			o1.x=o[0];
			o1.y=o[1];
			o1.z=o[2];
			if(dproduct(d1,n)!=0) {
				GLfloat tt=-dproduct(minus(o1,a1),n)/dproduct(d1,n);
				if((tt>=EPS)&&(tt<(1-EPS))) {
					p.x=o1.x+tt*d[0];
					p.y=o1.y+tt*d[1];
					p.z=o1.z+tt*d[2];
					if(pointInTriangle(p,a1,a2,a3)) returnValue=false;
				}
			}
		}
	}
	return returnValue;
}

Color phong(light l,Point P,Vector n,Color c) {
	Color returnValue;
	GLfloat lr,lg,lb,d;
	GLfloat ll[3]={l.posOfLight[0]-P.x,l.posOfLight[1]-P.y,l.posOfLight[2]-P.z}; // l vector
	d=sqrt(ll[0]*ll[0]+ll[1]*ll[1]+ll[2]*ll[2]);
	normalize(ll);
	GLfloat v[3]={camera[0]-P.x,camera[1]-P.y,camera[2]-P.z}; // viewer vector
	normalize(v);
	GLfloat h[3]={ll[0]+v[0],ll[1]+v[1],ll[2]+v[2]}; // half way vector
	normalize(h);
	lr=l.colOfLight[0]/(l.intOfLight[0]+l.intOfLight[1]*d+l.intOfLight[2]*d*d);
	lg=l.colOfLight[1]/(l.intOfLight[0]+l.intOfLight[1]*d+l.intOfLight[2]*d*d);
	lb=l.colOfLight[2]/(l.intOfLight[0]+l.intOfLight[1]*d+l.intOfLight[2]*d*d);
	ln1=max(0,ll[0]*n.x+ll[1]*n.y+ll[2]*n.z);//test
	if(obj==0) { //sphere
		returnValue.r=lr*(surfaces[spheres[count].surface_num].kd*c.r*max(0,ll[0]*n.x+ll[1]*n.y+ll[2]*n.z)
			+surfaces[spheres[count].surface_num].ks*max(0,pow(h[0]*n.x+h[1]*n.y+h[2]*n.z,surfaces[spheres[count].surface_num].shininess)));
		returnValue.g=lg*(surfaces[spheres[count].surface_num].kd*c.g*max(0,ll[0]*n.x+ll[1]*n.y+ll[2]*n.z)
			+surfaces[spheres[count].surface_num].ks*max(0,pow(h[0]*n.x+h[1]*n.y+h[2]*n.z,surfaces[spheres[count].surface_num].shininess)));
		returnValue.b=lb*(surfaces[spheres[count].surface_num].kd*c.b*max(0,ll[0]*n.x+ll[1]*n.y+ll[2]*n.z)
			+surfaces[spheres[count].surface_num].ks*max(0,pow(h[0]*n.x+h[1]*n.y+h[2]*n.z,surfaces[spheres[count].surface_num].shininess)));
	}
	else if(obj==1){ // polyhedron
		returnValue.r=lr*(surfaces[polyhedrons[count].surface_num].kd*c.r*max(0,ll[0]*n.x+ll[1]*n.y+ll[2]*n.z)
			+surfaces[polyhedrons[count].surface_num].ks*max(0,pow(h[0]*n.x+h[1]*n.y+h[2]*n.z,surfaces[polyhedrons[count].surface_num].shininess)));
		returnValue.g=lg*(surfaces[polyhedrons[count].surface_num].kd*c.g*max(0,ll[0]*n.x+ll[1]*n.y+ll[2]*n.z)
			+surfaces[polyhedrons[count].surface_num].ks*max(0,pow(h[0]*n.x+h[1]*n.y+h[2]*n.z,surfaces[polyhedrons[count].surface_num].shininess)));
		returnValue.b=lb*(surfaces[polyhedrons[count].surface_num].kd*c.b*max(0,ll[0]*n.x+ll[1]*n.y+ll[2]*n.z)
			+surfaces[polyhedrons[count].surface_num].ks*max(0,pow(h[0]*n.x+h[1]*n.y+h[2]*n.z,surfaces[polyhedrons[count].surface_num].shininess)));
	}
	else if(obj==2){ // trianglemesh
		returnValue.r=lr*(surfaces[trianglemeshes[count].surface_num].kd*c.r*max(0,ll[0]*n.x+ll[1]*n.y+ll[2]*n.z)
			+surfaces[trianglemeshes[count].surface_num].ks*max(0,pow(h[0]*n.x+h[1]*n.y+h[2]*n.z,surfaces[trianglemeshes[count].surface_num].shininess)));
		returnValue.g=lg*(surfaces[trianglemeshes[count].surface_num].kd*c.g*max(0,ll[0]*n.x+ll[1]*n.y+ll[2]*n.z)
			+surfaces[trianglemeshes[count].surface_num].ks*max(0,pow(h[0]*n.x+h[1]*n.y+h[2]*n.z,surfaces[trianglemeshes[count].surface_num].shininess)));
		returnValue.b=lb*(surfaces[trianglemeshes[count].surface_num].kd*c.b*max(0,ll[0]*n.x+ll[1]*n.y+ll[2]*n.z)
			+surfaces[trianglemeshes[count].surface_num].ks*max(0,pow(h[0]*n.x+h[1]*n.y+h[2]*n.z,surfaces[trianglemeshes[count].surface_num].shininess)));
	}
	return returnValue;
}

Ray reflect(Ray R,Point P,Vector n) {
	Ray returnValue;
	returnValue.xs=P.x;
	returnValue.ys=P.y;
	returnValue.zs=P.z;
	GLfloat v[3]={R.xs-P.x,R.ys-P.y,R.zs-P.z};
	normalize(v);
	returnValue.x=2*(n.x*v[0]+n.y*v[1]+n.z*v[2])*n.x-v[0];  //2(n.v)n-v
	returnValue.y=2*(n.x*v[0]+n.y*v[1]+n.z*v[2])*n.y-v[1];
	returnValue.z=2*(n.x*v[0]+n.y*v[1]+n.z*v[2])*n.z-v[2];
	GLfloat temp[3]={returnValue.x,returnValue.y,returnValue.z};
	normalize(temp);
	returnValue.x=temp[0];
	returnValue.y=temp[1];
	returnValue.z=temp[2];
	return returnValue;
}

Ray transmit(Ray R,Point P,Vector n) {
	Ray returnValue;
	returnValue.x=0;
	returnValue.y=0;
	returnValue.z=0;
	returnValue.xs=0;
	returnValue.ys=0;
	returnValue.zs=0;
	Vector v,u1,u2,w2;
	GLfloat delta1,delta2;
	v.x=R.x;
	v.y=R.y;
	v.z=R.z;
	v=product(-1,normalizeV(v));
	if(outside) {
		delta1=1;
		delta2=ior;
	}
	else {
		delta1=ior;
		delta2=1;
	}
	float cosfai1=dproduct(v,n);
	float sinfai1=sqrt(1-cosfai1*cosfai1);
	if((delta1/delta2*sinfai1)<=1) {
		float cosfai2=sqrt(1-(delta1*delta1/delta2/delta2)*sinfai1*sinfai1);
		u2=product(-cosfai2,n);
		u1=product(cosfai1,n);
		w2=product((delta1/delta2),minusV(u1,v));
		returnValue.x=u2.x+w2.x;
		returnValue.y=u2.y+w2.y;
		returnValue.z=u2.z+w2.z;
		returnValue.xs=P.x;
		returnValue.ys=P.y;
		returnValue.zs=P.z;
	}
	return returnValue;
}

Vector computeNormal(Point p) {
	Vector returnValue;
	if(obj==0) {
		Point c;
		c.x=spheres[count].center_point[0];
		c.y=spheres[count].center_point[1];
		c.z=spheres[count].center_point[2];
		if(outside) 
			returnValue=normalizeV(minus(p,c));
		else
			returnValue=normalizeV(minus(c,p));
		ior=surfaces[spheres[count].surface_num].ior;
	}
	if(obj==1) {
		if(outside) {
			returnValue.x=polyhedrons[count].face[count1][0];
			returnValue.y=polyhedrons[count].face[count1][1];
			returnValue.z=polyhedrons[count].face[count1][2];
		}
		else {
			returnValue.x=-polyhedrons[count].face[count1][0];
			returnValue.y=-polyhedrons[count].face[count1][1];
			returnValue.z=-polyhedrons[count].face[count1][2];
		}
		ior=surfaces[polyhedrons[count].surface_num].ior;
	}
	if(obj==2) {
		Point a1,a2,a3;
		a1.x=vertice[triangle[count1][0]][0];
		a1.y=vertice[triangle[count1][0]][1];
		a1.z=vertice[triangle[count1][0]][2];
		a2.x=vertice[triangle[count1][1]][0];
		a2.y=vertice[triangle[count1][1]][1];
		a2.z=vertice[triangle[count1][1]][2];
		a3.x=vertice[triangle[count1][2]][0];
		a3.y=vertice[triangle[count1][2]][1];
		a3.z=vertice[triangle[count1][2]][2];
		returnValue=normalizeV(cproduct(minus(a2,a1),minus(a3,a1)));
		ior=surfaces[trianglemeshes[count].surface_num].ior;
	}
	return returnValue;
}

Color trace(Ray R, int depth) {
	Color localC, reflectedC, transmittedC, tempC;
	Point P;
	Vector n;
	
	if(depth>MAX) return bgc;

	P=intersect(R);
	if(inter==false)  // no intersection
		return bgc;
	else n=computeNormal(P);
	if(obj==0) {  // sphere
		if(pigments[spheres[count].pigment_num].type==0) { // solid
			tempC.r=pigments[spheres[count].pigment_num].color1[0];
			tempC.g=pigments[spheres[count].pigment_num].color1[1];
			tempC.b=pigments[spheres[count].pigment_num].color1[2];
			localC.r=surfaces[spheres[count].surface_num].ka*lights[0].colOfLight[0]*tempC.r;
			localC.g=surfaces[spheres[count].surface_num].ka*lights[0].colOfLight[1]*tempC.g;
			localC.b=surfaces[spheres[count].surface_num].ka*lights[0].colOfLight[2]*tempC.b;
		}
		else { // checker
			int temp=floor(P.x/pigments[spheres[count].pigment_num].length)+floor(P.y/pigments[spheres[count].pigment_num].length)+floor(P.z/pigments[spheres[count].pigment_num].length);
			if((temp%2)==0) {
				tempC.r=pigments[spheres[count].pigment_num].color1[0];
				tempC.g=pigments[spheres[count].pigment_num].color1[1];
				tempC.b=pigments[spheres[count].pigment_num].color1[2];
				localC.r=surfaces[spheres[count].surface_num].ka*lights[0].colOfLight[0]*tempC.r;
				localC.g=surfaces[spheres[count].surface_num].ka*lights[0].colOfLight[1]*tempC.g;
				localC.b=surfaces[spheres[count].surface_num].ka*lights[0].colOfLight[2]*tempC.b;
			}
			else {
				tempC.r=pigments[spheres[count].pigment_num].color2[0];
				tempC.g=pigments[spheres[count].pigment_num].color2[1];
				tempC.b=pigments[spheres[count].pigment_num].color2[2];
				localC.r=surfaces[spheres[count].surface_num].ka*lights[0].colOfLight[0]*tempC.r;
				localC.g=surfaces[spheres[count].surface_num].ka*lights[0].colOfLight[1]*tempC.g;
				localC.b=surfaces[spheres[count].surface_num].ka*lights[0].colOfLight[2]*tempC.b;
			}
		}
		if(surfaces[spheres[count].surface_num].kr>0) { // reflect
			Ray Rr= reflect(R,P,n);
			reflectedC=trace(Rr,depth+1);
			localC.r=localC.r+surfaces[spheres[count].surface_num].kr*reflectedC.r;
			localC.g=localC.g+surfaces[spheres[count].surface_num].kr*reflectedC.g;
			localC.b=localC.b+surfaces[spheres[count].surface_num].kr*reflectedC.b;
		}
		if(surfaces[spheres[count].surface_num].kt>0) { //transmit
			Ray Rt= transmit(R,P,n);
			transmittedC=trace(Rt,depth+1);
			localC.r=localC.r+surfaces[spheres[count].surface_num].kt*transmittedC.r;
			localC.g=localC.g+surfaces[spheres[count].surface_num].kt*transmittedC.g;
			localC.b=localC.b+surfaces[spheres[count].surface_num].kt*transmittedC.b;
		}
	}
	else if(obj==1){  //polyhedron
		if(pigments[polyhedrons[count].pigment_num].type==0) { //solid
			tempC.r=pigments[polyhedrons[count].pigment_num].color1[0];
			tempC.g=pigments[polyhedrons[count].pigment_num].color1[1];
			tempC.b=pigments[polyhedrons[count].pigment_num].color1[2];
			localC.r=surfaces[polyhedrons[count].surface_num].ka*lights[0].colOfLight[0]*tempC.r;
			localC.g=surfaces[polyhedrons[count].surface_num].ka*lights[0].colOfLight[1]*tempC.g;
			localC.b=surfaces[polyhedrons[count].surface_num].ka*lights[0].colOfLight[2]*tempC.b;
		}
		else { // checker
			int temp=floor(P.x/pigments[polyhedrons[count].pigment_num].length)+floor(P.y/pigments[polyhedrons[count].pigment_num].length)+floor(P.z/pigments[polyhedrons[count].pigment_num].length);
			if((temp%2)==0) {
				tempC.r=pigments[polyhedrons[count].pigment_num].color1[0];
				tempC.g=pigments[polyhedrons[count].pigment_num].color1[1];
				tempC.b=pigments[polyhedrons[count].pigment_num].color1[2];
				localC.r=surfaces[polyhedrons[count].surface_num].ka*lights[0].colOfLight[0]*tempC.r;
				localC.g=surfaces[polyhedrons[count].surface_num].ka*lights[0].colOfLight[1]*tempC.g;
				localC.b=surfaces[polyhedrons[count].surface_num].ka*lights[0].colOfLight[2]*tempC.b;
			}
			else {
				tempC.r=pigments[polyhedrons[count].pigment_num].color2[0];
				tempC.g=pigments[polyhedrons[count].pigment_num].color2[1];
				tempC.b=pigments[polyhedrons[count].pigment_num].color2[2];
				localC.r=surfaces[polyhedrons[count].surface_num].ka*lights[0].colOfLight[0]*tempC.r;
				localC.g=surfaces[polyhedrons[count].surface_num].ka*lights[0].colOfLight[1]*tempC.g;
				localC.b=surfaces[polyhedrons[count].surface_num].ka*lights[0].colOfLight[2]*tempC.b;
			}
		}
		if(surfaces[polyhedrons[count].surface_num].kr>0) { // reflect
			Ray Rr= reflect(R,P,n);
			reflectedC=trace(Rr,depth+1);
			localC.r=localC.r+surfaces[polyhedrons[count].surface_num].kr*reflectedC.r;
			localC.g=localC.g+surfaces[polyhedrons[count].surface_num].kr*reflectedC.g;
			localC.b=localC.b+surfaces[polyhedrons[count].surface_num].kr*reflectedC.b;
		}
		if(surfaces[polyhedrons[count].surface_num].kt>0) { //transmit
			Ray Rt= transmit(R,P,n);
			transmittedC=trace(Rt,depth+1);
			localC.r=localC.r+surfaces[polyhedrons[count].surface_num].kt*transmittedC.r;
			localC.g=localC.g+surfaces[polyhedrons[count].surface_num].kt*transmittedC.g;
			localC.b=localC.b+surfaces[polyhedrons[count].surface_num].kt*transmittedC.b;
		}
	}
	else if(obj==2) { // trianglemesh
		if(pigments[trianglemeshes[count].pigment_num].type==0) { // solid
			tempC.r=pigments[trianglemeshes[count].pigment_num].color1[0];
			tempC.g=pigments[trianglemeshes[count].pigment_num].color1[1];
			tempC.b=pigments[trianglemeshes[count].pigment_num].color1[2];
			localC.r=surfaces[trianglemeshes[count].surface_num].ka*lights[0].colOfLight[0]*tempC.r;
			localC.g=surfaces[trianglemeshes[count].surface_num].ka*lights[0].colOfLight[1]*tempC.g;
			localC.b=surfaces[trianglemeshes[count].surface_num].ka*lights[0].colOfLight[2]*tempC.b;
		}
		else { // checker
			int temp=floor(P.x/pigments[trianglemeshes[count].pigment_num].length)+floor(P.y/pigments[trianglemeshes[count].pigment_num].length)+floor(P.z/pigments[trianglemeshes[count].pigment_num].length);
			if((temp%2)==0) {
				tempC.r=pigments[trianglemeshes[count].pigment_num].color1[0];
				tempC.g=pigments[trianglemeshes[count].pigment_num].color1[1];
				tempC.b=pigments[trianglemeshes[count].pigment_num].color1[2];
				localC.r=surfaces[trianglemeshes[count].surface_num].ka*lights[0].colOfLight[0]*tempC.r;
				localC.g=surfaces[trianglemeshes[count].surface_num].ka*lights[0].colOfLight[1]*tempC.g;
				localC.b=surfaces[trianglemeshes[count].surface_num].ka*lights[0].colOfLight[2]*tempC.b;
			}
			else {
				tempC.r=pigments[trianglemeshes[count].pigment_num].color2[0];
				tempC.g=pigments[trianglemeshes[count].pigment_num].color2[1];
				tempC.b=pigments[trianglemeshes[count].pigment_num].color2[2];
				localC.r=surfaces[trianglemeshes[count].surface_num].ka*lights[0].colOfLight[0]*tempC.r;
				localC.g=surfaces[trianglemeshes[count].surface_num].ka*lights[0].colOfLight[1]*tempC.g;
				localC.b=surfaces[trianglemeshes[count].surface_num].ka*lights[0].colOfLight[2]*tempC.b;
			}
		}
		if(surfaces[trianglemeshes[count].surface_num].kr>0) { // reflect
			Ray Rr= reflect(R,P,n);
			reflectedC=trace(Rr,depth+1);
			localC.r=localC.r+surfaces[trianglemeshes[count].surface_num].kr*reflectedC.r;
			localC.g=localC.g+surfaces[trianglemeshes[count].surface_num].kr*reflectedC.g;
			localC.b=localC.b+surfaces[trianglemeshes[count].surface_num].kr*reflectedC.b;
		}
		if(surfaces[trianglemeshes[count].surface_num].kt>0) { //transmit
			Ray Rt= transmit(R,P,n);
			transmittedC=trace(Rt,depth+1);
			localC.r=localC.r+surfaces[trianglemeshes[count].surface_num].kt*transmittedC.r;
			localC.g=localC.g+surfaces[trianglemeshes[count].surface_num].kt*transmittedC.g;
			localC.b=localC.b+surfaces[trianglemeshes[count].surface_num].kt*transmittedC.b;
		}
	}
	for(int i=1; i<numOfLight; i++) {
		if (visible(P,lights[i])) {
			Color temp=phong(lights[i],P,n,tempC);
			localC.r=localC.r+temp.r;
			localC.g=localC.g+temp.g;
			localC.b=localC.b+temp.b;
		}
	}
	
	return localC;
}

// write PPM
void writePPM()
{
	FILE *fp = fopen(outFile,"wb");
	fprintf(fp,"P6\n%d %d\n255\n",width,height);

	GLfloat h=2*tan(fovy*3.1415926/360); // height
	GLfloat w=h*width/(float)height; // width
	GLfloat cz1[3]={at[0]-camera[0],at[1]-camera[1],at[2]-camera[2]};  // A-E
	normalize(cz1);
	GLfloat cz[3]={-cz1[0],-cz1[1],-cz1[2]};
	GLfloat cx[3]={up[1]*cz[2]-cz[1]*up[2],up[2]*cz[0]-up[0]*cz[2],up[0]*cz[1]-cz[0]*up[1]};  // Up X C.z
	normalize(cx);
	GLfloat cy[3]={cz[1]*cx[2]-cx[1]*cz[2],cz[2]*cx[0]-cz[0]*cx[2],cz[0]*cx[1]-cx[0]*cz[1]}; // C.z X C.x
	for(int i=0;i<height;i++) {
		for(int j=0;j<width;j++) {
			GLfloat px=w*j/(float)width-w/2; // in camera coordinates
			GLfloat py=-h*i/(float)height+h/2;
			GLfloat r[3]={px*cx[0]+py*cy[0]-cz[0],px*cx[1]+py*cy[1]-cz[1],px*cx[2]+py*cy[2]-cz[2]};
			Ray R;
			R.xs=camera[0];
			R.ys=camera[1];
			R.zs=camera[2];
			R.x=r[0];
			R.y=r[1];
			R.z=r[2];
			Color c;
			if(!antiA)
				c=trace(R,1);
			else{ // antialiasing
				c.r=0;
				c.g=0;
				c.b=0;
				for(int m=0;m<3;m++) {
					for(int n=0;n<3;n++) {
						GLfloat px=w*(j+(float)n/2)/(float)width-w/2;
						GLfloat py=-h*(i+(float)m/2)/(float)height+h/2;
						GLfloat r[3]={px*cx[0]+py*cy[0]-cz[0],px*cx[1]+py*cy[1]-cz[1],px*cx[2]+py*cy[2]-cz[2]};
						Ray R;
						R.xs=camera[0];
						R.ys=camera[1];
						R.zs=camera[2];
						R.x=r[0];
						R.y=r[1];
						R.z=r[2];
						if((m==0&&n==0)||(m==0&&n==2)||(m==2&&n==0)||(m==2&&n==2)) {
							Color tempC=trace(R,1);
							c.r+=tempC.r/16;
							c.g+=tempC.g/16;
							c.b+=tempC.b/16;
						}
						if((m==0&&n==1)||(m==1&&n==0)||(m==1&&n==2)||(m==2&&n==1)) {
							Color tempC=trace(R,1);
							c.r+=tempC.r/8;
							c.g+=tempC.g/8;
							c.b+=tempC.b/8;
						}
						if(m==1&&n==1) {
							Color tempC=trace(R,1);
							c.r+=tempC.r/4;
							c.g+=tempC.g/4;
							c.b+=tempC.b/4;
						}
					}
				}
			}
			if(c.r<0) c.r=0;
			if(c.r>1) c.r=1;
			if(c.g<0) c.g=0;
			if(c.g>1) c.g=1;
			if(c.b<0) c.b=0;
			if(c.b>1) c.b=1;
			int d0=(int)(c.r*255);
			int d1=(int)(c.g*255);
			int d2=(int)(c.b*255);
			unsigned char t0=(unsigned char)d0;
			unsigned char t1=(unsigned char)d1;
			unsigned char t2=(unsigned char)d2;
			data[i][j][0]=d0;
			data[i][j][1]=d1;
			data[i][j][2]=d2;
			pigmData[i][j][0]=pigments[spheres[count].pigment_num].color1[0];//test
			pigmData[i][j][1]=pigments[spheres[count].pigment_num].color1[1];//test
			pigmData[i][j][2]=pigments[spheres[count].pigment_num].color1[2];//test
			object[i][j]=obj;//test
			ln[i][j]=ln1;//test
			kds[i][j]=surfaces[trianglemeshes[count].surface_num].kd;//test
			//printf("%d  %d  %d  %d  %d\n",i,j,d0,d1,d2);
			unsigned char color[3];
			color[0]=t0;
			color[1]=t1;
			color[2]=t2;
			fwrite(color,1,3,fp);
		}
	}
	fclose(fp);
}

void debug(){
	int x,y;
	printf("Please enter the x and y coordinates:");
	scanf("%d %d",&x,&y);
	printf("red:%d  green:%d  blue:%d  %f  %f  %f %d l.n:%f  kd:%f\n",data[y][x][0],data[y][x][1],data[y][x][2],pigmData[y][x][0],pigmData[y][x][1],pigmData[y][x][2],object[y][x],ln[y][x],kds[y][x]);//test
}

int main(int argc, char **argv){

	printf("Please enter the input file name:");
	scanf("%s",inFile);
	readFile(inFile);
	//Sleep(40000);

	writePPM();
	while(true)
		debug();
	return 0;
}