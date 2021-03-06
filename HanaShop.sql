USE [Hana Shop]
GO
/****** Object:  Table [dbo].[User]    Script Date: 1/20/2021 9:51:02 PM ******/
DROP TABLE [dbo].[User]
GO
/****** Object:  Table [dbo].[History]    Script Date: 1/20/2021 9:51:02 PM ******/
DROP TABLE [dbo].[History]
GO
/****** Object:  Table [dbo].[Foods]    Script Date: 1/20/2021 9:51:02 PM ******/
DROP TABLE [dbo].[Foods]
GO
/****** Object:  Table [dbo].[Bill]    Script Date: 1/20/2021 9:51:02 PM ******/
DROP TABLE [dbo].[Bill]
GO
USE [master]
GO
/****** Object:  Database [Hana Shop]    Script Date: 1/20/2021 9:51:02 PM ******/
DROP DATABASE [Hana Shop]
GO
/****** Object:  Database [Hana Shop]    Script Date: 1/20/2021 9:51:02 PM ******/
CREATE DATABASE [Hana Shop]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Hana Shop', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\Hana Shop.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'Hana Shop_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.MSSQLSERVER\MSSQL\DATA\Hana Shop_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [Hana Shop] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Hana Shop].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Hana Shop] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Hana Shop] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Hana Shop] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Hana Shop] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Hana Shop] SET ARITHABORT OFF 
GO
ALTER DATABASE [Hana Shop] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Hana Shop] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Hana Shop] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Hana Shop] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Hana Shop] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Hana Shop] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Hana Shop] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Hana Shop] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Hana Shop] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Hana Shop] SET  DISABLE_BROKER 
GO
ALTER DATABASE [Hana Shop] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Hana Shop] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Hana Shop] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Hana Shop] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Hana Shop] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Hana Shop] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Hana Shop] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Hana Shop] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [Hana Shop] SET  MULTI_USER 
GO
ALTER DATABASE [Hana Shop] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Hana Shop] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Hana Shop] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Hana Shop] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Hana Shop] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [Hana Shop] SET QUERY_STORE = OFF
GO
USE [Hana Shop]
GO
/****** Object:  Table [dbo].[Bill]    Script Date: 1/20/2021 9:51:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Bill](
	[billID] [varchar](50) NOT NULL,
	[username] [varchar](50) NOT NULL,
	[createDate] [date] NOT NULL,
	[description] [varchar](max) NOT NULL,
	[total] [float] NOT NULL,
 CONSTRAINT [PK_Bill] PRIMARY KEY CLUSTERED 
(
	[billID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Foods]    Script Date: 1/20/2021 9:51:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Foods](
	[id] [varchar](50) NOT NULL,
	[name] [varchar](256) NULL,
	[image] [varchar](max) NULL,
	[description] [varchar](max) NULL,
	[price] [float] NULL,
	[createDate] [datetime] NULL,
	[category] [varchar](50) NULL,
	[quantity] [int] NULL,
	[status] [bit] NULL,
 CONSTRAINT [PK_Food] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[History]    Script Date: 1/20/2021 9:51:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[History](
	[id] [varchar](100) NOT NULL,
	[createDate] [datetime] NULL,
	[description] [varchar](max) NULL,
 CONSTRAINT [PK_History] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[User]    Script Date: 1/20/2021 9:51:03 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User](
	[username] [varchar](50) NOT NULL,
	[password] [varchar](50) NOT NULL,
	[role] [bit] NOT NULL,
	[name] [varchar](50) NOT NULL,
	[address] [varchar](max) NULL,
	[phoneNumber] [varchar](max) NULL,
 CONSTRAINT [PK_Login] PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
INSERT [dbo].[Foods] ([id], [name], [image], [description], [price], [createDate], [category], [quantity], [status]) VALUES (N'1', N'COMBO FRIED CHICKEN A', N'image/Screenshot 2021-01-20 201122.png', N'2 Pcs of Hot & Spicy Chicken / 2 Pcs of Non Spicy Crispy Chicken / 2', 2, CAST(N'2021-01-20T20:17:31.270' AS DateTime), N'Foods', 4, 1)
INSERT [dbo].[Foods] ([id], [name], [image], [description], [price], [createDate], [category], [quantity], [status]) VALUES (N'10', N'Kiwi Chiller', N'image/kiwichiller.png', N'1 Kiwi Chiller', 0.3, CAST(N'2021-01-20T20:38:43.623' AS DateTime), N'Drinks', 30, 1)
INSERT [dbo].[Foods] ([id], [name], [image], [description], [price], [createDate], [category], [quantity], [status]) VALUES (N'11', N'Ice Latte', N'image/icedlatte.png', N'1 Ice Latte', 0.30000001192092896, CAST(N'2021-01-20T21:14:51.787' AS DateTime), N'Drinks', 30, 1)
INSERT [dbo].[Foods] ([id], [name], [image], [description], [price], [createDate], [category], [quantity], [status]) VALUES (N'12', N'Teppy orange drink', N'image/teppy_orange_drink.png', N'1 Teppy orange drink', 0.20000000298023224, CAST(N'2021-01-20T21:08:29.067' AS DateTime), N'Drinks', 30, 1)
INSERT [dbo].[Foods] ([id], [name], [image], [description], [price], [createDate], [category], [quantity], [status]) VALUES (N'13', N'Peach Tea', N'image/CSO_PeachTea_S.png', N'1 Peach Tea', 3, CAST(N'2021-01-20T21:11:40.293' AS DateTime), N'Drinks', 30, 1)
INSERT [dbo].[Foods] ([id], [name], [image], [description], [price], [createDate], [category], [quantity], [status]) VALUES (N'14', N'Chocolate Cookie', N'image/CHOCOLATE-CHIP-COOKIE.png', N'1 Chocolate Cookie', 0.20000000298023224, CAST(N'2021-01-20T21:15:56.257' AS DateTime), N'Foods', 100, 1)
INSERT [dbo].[Foods] ([id], [name], [image], [description], [price], [createDate], [category], [quantity], [status]) VALUES (N'15', N'Grilled Pork Egg Rice', N'image/grilled_porkegg_rice.png', N'1 Grilled Pork Egg Rice', 1, CAST(N'2021-01-20T21:24:14.420' AS DateTime), N'Foods', 100, 1)
INSERT [dbo].[Foods] ([id], [name], [image], [description], [price], [createDate], [category], [quantity], [status]) VALUES (N'16', N'Green Tea', N'image/GREENTEA-MUFFIN.png', N'1 Green Tea', 3, CAST(N'2021-01-20T21:25:40.267' AS DateTime), N'Drinks', 100, 1)
INSERT [dbo].[Foods] ([id], [name], [image], [description], [price], [createDate], [category], [quantity], [status]) VALUES (N'17', N'Capucino', N'image/cappucino.png', N'1 cup of Capucino', 2, CAST(N'2021-01-20T21:25:30.827' AS DateTime), N'Drinks', 100, 1)
INSERT [dbo].[Foods] ([id], [name], [image], [description], [price], [createDate], [category], [quantity], [status]) VALUES (N'18', N'Tiramisu', N'image/TIRAMISU.png', N'1 Tiramisu', 3, CAST(N'2021-01-20T21:30:55.817' AS DateTime), N'Foods', 100, 1)
INSERT [dbo].[Foods] ([id], [name], [image], [description], [price], [createDate], [category], [quantity], [status]) VALUES (N'19', N'Combo Fried Chicken Wing ', N'image/6-wings.png', N'6 Fried Chicken Wings ', 8, CAST(N'2021-01-20T21:31:50.260' AS DateTime), N'Foods', 100, 1)
INSERT [dbo].[Foods] ([id], [name], [image], [description], [price], [createDate], [category], [quantity], [status]) VALUES (N'2', N'COMBO FRIED CHICKEN B', N'image/Screenshot 2021-01-20 201147.png', N'1 Hot Wings 3 Pcs1 French Fries (L)1 Pepsi Can', 2.5, CAST(N'2021-01-20T20:17:45.590' AS DateTime), N'Foods', 2, 1)
INSERT [dbo].[Foods] ([id], [name], [image], [description], [price], [createDate], [category], [quantity], [status]) VALUES (N'20', N'Baked Cheese Cake', N'image/BAKED-CHEESECAKE.png', N'1 Baked Cheese Cake', 2.5, CAST(N'2021-01-20T21:32:44.010' AS DateTime), N'Foods', 100, 1)
INSERT [dbo].[Foods] ([id], [name], [image], [description], [price], [createDate], [category], [quantity], [status]) VALUES (N'21', N'Caramel Frappe', N'image/caramelfrappe.png', N'1 Caramel Frappe', 2.9000000953674316, CAST(N'2021-01-20T21:34:26.063' AS DateTime), N'Foods', 100, 1)
INSERT [dbo].[Foods] ([id], [name], [image], [description], [price], [createDate], [category], [quantity], [status]) VALUES (N'3', N'Chicken', N'image/1-ga-ran.png', N'1 fried chicken', 10, CAST(N'2021-01-20T20:29:50.183' AS DateTime), N'Foods', 3, 1)
INSERT [dbo].[Foods] ([id], [name], [image], [description], [price], [createDate], [category], [quantity], [status]) VALUES (N'4', N'Flava Roast Fillet Rice (1 Pc)', N'image/Screenshot 2021-01-20 201248.png', N'Flava Roast Fillet Rice (1 Pc)', 2, CAST(N'2021-01-20T20:20:05.983' AS DateTime), N'Foods', 30, 1)
INSERT [dbo].[Foods] ([id], [name], [image], [description], [price], [createDate], [category], [quantity], [status]) VALUES (N'5', N'Combo Fried Chicken', N'image/9-ga-ran.png', N'9 Fried Chicken', 12, CAST(N'2021-01-20T20:30:53.997' AS DateTime), N'Foods', 30, 1)
INSERT [dbo].[Foods] ([id], [name], [image], [description], [price], [createDate], [category], [quantity], [status]) VALUES (N'6', N'Hamburger', N'image/mcroyal-deluxe.png', N'1 Hamburger', 2, CAST(N'2021-01-20T20:32:24.410' AS DateTime), N'Foods', 30, 1)
INSERT [dbo].[Foods] ([id], [name], [image], [description], [price], [createDate], [category], [quantity], [status]) VALUES (N'7', N'Ice cream', N'image/mcdonalds_cone.png', N'1 Ice Cream', 0.3, CAST(N'2021-01-20T20:36:16.917' AS DateTime), N'Foods', 30, 1)
INSERT [dbo].[Foods] ([id], [name], [image], [description], [price], [createDate], [category], [quantity], [status]) VALUES (N'8', N'Coca Cola', N'image/mcd-food-beverages-soft-drinks-coke.png', N'1 Coca Cola', 0.5, CAST(N'2021-01-20T20:37:11.417' AS DateTime), N'Drinks', 30, 1)
INSERT [dbo].[Foods] ([id], [name], [image], [description], [price], [createDate], [category], [quantity], [status]) VALUES (N'9', N'Dasani Water', N'image/dasani_water.png', N'1 Dasani Water', 0.20000000298023224, CAST(N'2021-01-20T21:29:50.370' AS DateTime), N'Drinks', 30, 1)
INSERT [dbo].[History] ([id], [createDate], [description]) VALUES (N'20210120201731', CAST(N'2021-01-20T20:17:31.277' AS DateTime), N'nam updated a new object at Wed Jan 20 20:17:31 ICT 2021')
INSERT [dbo].[History] ([id], [createDate], [description]) VALUES (N'20210120201745', CAST(N'2021-01-20T20:17:45.613' AS DateTime), N'nam updated a new object at Wed Jan 20 20:17:45 ICT 2021')
INSERT [dbo].[History] ([id], [createDate], [description]) VALUES (N'20210120201848', CAST(N'2021-01-20T20:18:48.627' AS DateTime), N'nam updated a new object at Wed Jan 20 20:18:48 ICT 2021')
INSERT [dbo].[History] ([id], [createDate], [description]) VALUES (N'20210120202950', CAST(N'2021-01-20T20:29:50.193' AS DateTime), N'nam updated a new object at Wed Jan 20 20:29:50 ICT 2021')
INSERT [dbo].[History] ([id], [createDate], [description]) VALUES (N'20210120203711', CAST(N'2021-01-20T20:37:11.447' AS DateTime), N'nam updated a new object at Wed Jan 20 20:37:11 ICT 2021')
INSERT [dbo].[History] ([id], [createDate], [description]) VALUES (N'20210120211140', CAST(N'2021-01-20T21:11:40.303' AS DateTime), N'nam updated a new object at Wed Jan 20 21:11:40 ICT 2021')
INSERT [dbo].[History] ([id], [createDate], [description]) VALUES (N'20210120211451', CAST(N'2021-01-20T21:14:51.840' AS DateTime), N'nam updated a new object at Wed Jan 20 21:14:51 ICT 2021')
INSERT [dbo].[History] ([id], [createDate], [description]) VALUES (N'20210120212404', CAST(N'2021-01-20T21:24:04.167' AS DateTime), N'nam updated a new object at Wed Jan 20 21:24:04 ICT 2021')
INSERT [dbo].[History] ([id], [createDate], [description]) VALUES (N'20210120212414', CAST(N'2021-01-20T21:24:14.430' AS DateTime), N'nam updated a new object at Wed Jan 20 21:24:14 ICT 2021')
INSERT [dbo].[History] ([id], [createDate], [description]) VALUES (N'20210120212540', CAST(N'2021-01-20T21:25:40.273' AS DateTime), N'nam updated a new object at Wed Jan 20 21:25:40 ICT 2021')
INSERT [dbo].[History] ([id], [createDate], [description]) VALUES (N'20210120212950', CAST(N'2021-01-20T21:29:50.377' AS DateTime), N'nam updated a new object at Wed Jan 20 21:29:50 ICT 2021')
INSERT [dbo].[User] ([username], [password], [role], [name], [address], [phoneNumber]) VALUES (N'nam', N'123', 1, N'Huy', NULL, NULL)
INSERT [dbo].[User] ([username], [password], [role], [name], [address], [phoneNumber]) VALUES (N'user', N'123', 0, N'Nam', N'1248124', N'1234556')
USE [master]
GO
ALTER DATABASE [Hana Shop] SET  READ_WRITE 
GO
